package com.app.springapp.service;

import com.app.springapp.domain.dto.request.SuggestionCreateRequestDTO;
import com.app.springapp.domain.dto.response.SuggestionResponseDTO;
import com.app.springapp.domain.vo.ChecklistVO;
import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.domain.vo.SuggestionVO;
import com.app.springapp.repository.ProjectDAO;
import com.app.springapp.repository.SuggestionDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionDAO suggestionDAO;
    private final ProjectDAO projectDAO;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api-key}")
    private String openAiApiKey;

    @Value("${openai.model:gpt-4o-mini}")
    private String openAiModel;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private static final int CANDIDATE_LIMIT = 20;

    // 제안 작성 - DTO를 VO로 변환 후 저장
    @Override
    public void createSuggestion(SuggestionCreateRequestDTO requestDTO, Long memberId) {
        SuggestionVO vo = new SuggestionVO();
        vo.setSuggestionTitle(requestDTO.getSuggestionTitle());
        vo.setChecklistId(requestDTO.getChecklistId());
        vo.setProjectId(requestDTO.getProjectId());
        vo.setMemberId(memberId);
        vo.setIsAddedList("N");
        suggestionDAO.save(vo);
    }

    // 프로젝트별 제안 목록 조회 - VO를 ResponseDTO로 변환 후 반환
    @Override
    public List<SuggestionResponseDTO> getSuggestionsByProjectId(Long projectId) {
        return suggestionDAO.findAllByProjectId(projectId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 프로젝트 초기 - AI가 선별한 다른 사용자의 추천 제안 최대 3개 반환
    @Override
    public List<SuggestionResponseDTO> getRecommendedSuggestions(Long projectId) {
        List<SuggestionVO> candidates = suggestionDAO.findTopSuggestionsExcludingProject(projectId, CANDIDATE_LIMIT);

        if (candidates.isEmpty()) {
            return Collections.emptyList();
        }

        if (candidates.size() <= 3) {
            return candidates.stream().map(this::toResponseDTO).collect(Collectors.toList());
        }

        ProjectVO project = projectDAO.findById(projectId);
        if (project == null) {
            return candidates.subList(0, 3).stream().map(this::toResponseDTO).collect(Collectors.toList());
        }

        List<ChecklistVO> checklists = projectDAO.findChecklistsByProjectId(projectId);

        try {
            List<Integer> selectedIndices = selectByAI(project, checklists, candidates);
            List<SuggestionVO> selected = selectedIndices.stream()
                    .filter(i -> i >= 1 && i <= candidates.size())
                    .map(i -> candidates.get(i - 1))
                    .limit(3)
                    .collect(Collectors.toList());

            if (selected.isEmpty()) {
                return candidates.subList(0, Math.min(3, candidates.size())).stream()
                        .map(this::toResponseDTO).collect(Collectors.toList());
            }
            return selected.stream().map(this::toResponseDTO).collect(Collectors.toList());

        } catch (Exception e) {
            log.warn("AI 추천 제안 선별 실패, 상위 3개 fallback: {}", e.getMessage());
            return candidates.subList(0, Math.min(3, candidates.size())).stream()
                    .map(this::toResponseDTO).collect(Collectors.toList());
        }
    }

    // OpenAI에게 후보 중 가장 어울리는 제안 번호 선택 요청
    private List<Integer> selectByAI(ProjectVO project, List<ChecklistVO> checklists, List<SuggestionVO> candidates) throws Exception {
        StringBuilder prompt = new StringBuilder();
        prompt.append("아래 프로젝트에 가장 잘 맞는 제안을 후보 목록에서 최대 3개 선택해주세요.\n\n");

        prompt.append("[프로젝트 정보]\n");
        prompt.append("- 제목: ").append(project.getProjectTitle()).append("\n");
        if (project.getVisionTitle() != null && !project.getVisionTitle().isBlank()) {
            prompt.append("- 비전: ").append(project.getVisionTitle()).append("\n");
        }
        if (checklists != null && !checklists.isEmpty()) {
            prompt.append("- 체크리스트:\n");
            for (ChecklistVO c : checklists) {
                prompt.append("  * ").append(c.getChecklistTitle()).append("\n");
            }
        }

        prompt.append("\n[후보 제안 목록]\n");
        for (int i = 0; i < candidates.size(); i++) {
            prompt.append((i + 1)).append(". ").append(candidates.get(i).getSuggestionTitle()).append("\n");
        }

        prompt.append("\n반드시 JSON 숫자 배열 형식으로만 응답하세요. 예: [1, 3, 7]\n");
        prompt.append("프로젝트와 가장 잘 어울리는 제안 번호를 최대 3개 선택하세요. 어울리는 것이 없으면 [] 로 응답하세요.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        Map<String, Object> body = Map.of(
                "model", openAiModel,
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "당신은 프로젝트 제안 추천 전문가입니다. 요청한 JSON 배열 형식으로만 응답하세요."),
                        Map.of("role", "user", "content", prompt.toString())
                ),
                "temperature", 0.3,
                "max_tokens", 50
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, entity, String.class);

        JsonNode root = objectMapper.readTree(response.getBody());
        String content = root.path("choices").get(0).path("message").path("content").asText().trim();

        log.info("AI 추천 제안 선별 응답: {}", content);

        JsonNode arrayNode = objectMapper.readTree(content);
        List<Integer> indices = new ArrayList<>();
        if (arrayNode.isArray()) {
            arrayNode.forEach(node -> indices.add(node.asInt()));
        }
        return indices;
    }

    private SuggestionResponseDTO toResponseDTO(SuggestionVO vo) {
        SuggestionResponseDTO dto = new SuggestionResponseDTO();
        dto.setId(vo.getId());
        dto.setSuggestionTitle(vo.getSuggestionTitle());
        dto.setIsAddedList(vo.getIsAddedList());
        dto.setChecklistId(vo.getChecklistId());
        dto.setMemberId(vo.getMemberId());
        dto.setProjectId(vo.getProjectId());
        dto.setMemberNickname(vo.getMemberNickname());
        dto.setMemberProfileImageUrl(vo.getMemberProfileImageUrl());
        return dto;
    }
}
