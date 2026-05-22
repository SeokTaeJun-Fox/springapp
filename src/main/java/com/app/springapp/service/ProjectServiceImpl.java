package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ProjectCreateRequestDTO;
import com.app.springapp.domain.dto.response.ChecklistResponseDTO;
import com.app.springapp.domain.dto.response.ProjectResponseDTO;
import com.app.springapp.domain.vo.LogResultVO;
import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.repository.ChecklistDAO;
import com.app.springapp.repository.LogResultDAO;
import com.app.springapp.repository.ProjectDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 프로젝트 서비스 구현체 - 프로젝트 생성(AI), 조회, 수정, 삭제 비즈니스 로직 처리
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDAO projectDAO;
    private final LogResultDAO logResultDAO;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ChecklistDAO checklistDAO;

    @Value("${openai.api-key}")
    private String openAiApiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    // ────────────────────────────────────────────────
    // 프로젝트 생성 (AI)
    // ────────────────────────────────────────────────

    /**
     * AI 기반 프로젝트 자동 생성
     * - 로그 분석 결과를 기반으로 OpenAI API를 호출하여 프로젝트 정보 및 행동 추천 4개를 생성
     * - 생성된 프로젝트를 DB에 저장 후 저장된 데이터를 다시 조회하여 반환
     *
     * @param requestDTO 프로젝트 생성 요청 정보 (로그 ID 포함)
     * @param memberId   현재 로그인한 회원 ID
     * @return 생성된 프로젝트 정보 (ProjectResponseDTO)
     */
    @Override
    public ProjectResponseDTO createProjectByAI(ProjectCreateRequestDTO requestDTO, Long memberId) {

        LogResultVO logResult = logResultDAO.findByLogId(requestDTO.getLogId());
        if (logResult == null) {
            throw new RuntimeException("해당 로그의 분석 결과가 없습니다. 먼저 로그 분석을 완료해주세요.");
        }

        AiProjectResult aiResult = generateProjectByAI(logResult);

        ProjectVO projectVO = new ProjectVO();
        projectVO.setMemberId(memberId);
        projectVO.setLogId(requestDTO.getLogId());
        projectVO.setProjectTitle(aiResult.getProjectTitle());
        projectVO.setProjectContent(aiResult.getProjectContent());
        projectVO.setProgressDay(aiResult.getProgressDay());
        projectVO.setProjectStartDate(aiResult.getProjectStartDate());
        projectVO.setProjectEndDate(aiResult.getProjectEndDate());

        // AI 행동 추천 저장
        List<ProjectResponseDTO.AiSuggestionItem> aiSuggestions = aiResult.getAiSuggestions();
        if (aiSuggestions != null && aiSuggestions.size() >= 4) {
            projectVO.setAiSuggestion1Title(aiSuggestions.get(0).getTitle());
            projectVO.setAiSuggestion1Desc(aiSuggestions.get(0).getDesc());
            projectVO.setAiSuggestion2Title(aiSuggestions.get(1).getTitle());
            projectVO.setAiSuggestion2Desc(aiSuggestions.get(1).getDesc());
            projectVO.setAiSuggestion3Title(aiSuggestions.get(2).getTitle());
            projectVO.setAiSuggestion3Desc(aiSuggestions.get(2).getDesc());
            projectVO.setAiSuggestion4Title(aiSuggestions.get(3).getTitle());
            projectVO.setAiSuggestion4Desc(aiSuggestions.get(3).getDesc());
        }

        log.info("INSERT 시작 - projectVO: {}", projectVO);
        projectDAO.insertProject(projectVO);
        log.info("INSERT 완료 - 생성된 ID: {}", projectVO.getId());

        ProjectResponseDTO result = toResponseDTO(projectDAO.findById(projectVO.getId()));
        log.info("프로젝트 조회 완료 - result: {}", result);

        return result;
    }

    // ────────────────────────────────────────────────
    // OpenAI 호출
    // ────────────────────────────────────────────────

    /**
     * OpenAI API를 호출하여 프로젝트 정보 생성
     * - 로그 분석 결과를 프롬프트로 변환 후 OpenAI에 전송
     * - 응답받은 JSON을 AiProjectResult 객체로 파싱하여 반환
     *
     * @param logResult 로그 분석 결과 (LogResultVO)
     * @return AI가 생성한 프로젝트 정보 (AiProjectResult)
     */
    private AiProjectResult generateProjectByAI(LogResultVO logResult) {

        log.info("===== OpenAI API 호출 시작 - logId: {} =====", logResult.getLogId());

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String prompt = buildPrompt(logResult, today);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "당신은 실패 분석 결과를 바탕으로 실천 가능한 개선 프로젝트를 설계해주는 코치입니다. " +
                                        "반드시 JSON 형식으로만 응답하세요. 추가 설명 없이 JSON만 출력하세요."),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7,
                "max_tokens", 600
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            log.info("OpenAI 요청 전송 중... URL: {}", OPENAI_URL);
            ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, entity, String.class);
            log.info("OpenAI 응답 수신 완료 - 상태코드: {}", response.getStatusCode());
            log.info("OpenAI 응답 내용: {}", response.getBody());
            return parseAiResponse(response.getBody());
        } catch (Exception e) {
            log.error("OpenAI API 호출 실패: {}", e.getMessage(), e);
            throw new RuntimeException("AI 프로젝트 생성에 실패했습니다.");
        }
    }

    // 로그 분석 결과를 OpenAI 프롬프트로 변환
    private String buildPrompt(LogResultVO logResult, String today) {
        return String.format("""
        아래는 사용자의 실패 분석 결과입니다.
        
        [실패 유형] %s
        [실패 제목] %s
        [실패 설명] %s
        [한 줄 요약] %s
        [핵심 의사결정] %s
        [외부 요인] %s
        [내부 요인] %s
        [외부/내부 비율] 외부 %d%% / 내부 %d%%
        [오늘 날짜] %s
        
        위 분석을 바탕으로 사용자가 실행할 수 있는 개선 프로젝트와 행동 추천을 설계해주세요.
        - 프로젝트 시작일은 오늘(%s)로 설정하세요.
        - 프로젝트 기간은 실패 유형과 목표 난이도에 따라 1개월~6개월 사이로 적절히 설정하세요.
        - progressDay는 시작일 기준 "D+0"으로 설정하세요.
        - 행동 추천은 실패 원인을 개선하기 위한 구체적인 행동 4개를 제시해주세요.
        
        반드시 아래 JSON 형식으로만 응답하세요:
        {
          "projectTitle": "프로젝트 제목 (30자 이내)",
          "projectContent": "프로젝트 목표와 방향 설명 (100자 이내)",
          "projectStartDate": "yyyy-MM-dd",
          "projectEndDate": "yyyy-MM-dd",
          "progressDay": "D+0",
          "aiSuggestions": [
            { "title": "행동 제목 (20자 이내)", "desc": "행동 설명 (50자 이내)" },
            { "title": "행동 제목 (20자 이내)", "desc": "행동 설명 (50자 이내)" },
            { "title": "행동 제목 (20자 이내)", "desc": "행동 설명 (50자 이내)" },
            { "title": "행동 제목 (20자 이내)", "desc": "행동 설명 (50자 이내)" }
          ]
        }
        """,
                logResult.getLogResultFailureType(),
                logResult.getLogResultFailureTitle(),
                logResult.getLogResultFailureDesc(),
                logResult.getLogResultOneLineSummary(),
                logResult.getLogResultLogKeyDecision(),
                logResult.getLogResultLogExternalFactor(),
                logResult.getLogResultLogInternalFactor(),
                logResult.getLogResultExternalRatio(),
                logResult.getLogResultInternalRatio(),
                today,
                today
        );
    }

    // OpenAI 응답 JSON을 AiProjectResult 객체로 파싱
    private AiProjectResult parseAiResponse(String responseBody) throws Exception {
        JsonNode root = objectMapper.readTree(responseBody);
        String content = root.path("choices").get(0).path("message").path("content").asText();

        content = content.replaceAll("```json", "").replaceAll("```", "").trim();

        log.info("파싱할 AI 응답 content: {}", content);

        JsonNode result = objectMapper.readTree(content);

        AiProjectResult aiResult = new AiProjectResult();
        aiResult.setProjectTitle(result.path("projectTitle").asText());
        aiResult.setProjectContent(result.path("projectContent").asText());
        aiResult.setProjectStartDate(result.path("projectStartDate").asText(
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        aiResult.setProjectEndDate(result.path("projectEndDate").asText(
                LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        aiResult.setProgressDay(result.path("progressDay").asText("D+0"));

        // aiSuggestions 파싱
        List<ProjectResponseDTO.AiSuggestionItem> aiSuggestions = new ArrayList<>();
        JsonNode suggestionsNode = result.path("aiSuggestions");
        if (suggestionsNode.isArray()) {
            for (JsonNode node : suggestionsNode) {
                ProjectResponseDTO.AiSuggestionItem item = new ProjectResponseDTO.AiSuggestionItem();
                item.setTitle(node.path("title").asText());
                item.setDesc(node.path("desc").asText());
                aiSuggestions.add(item);
            }
        }
        aiResult.setAiSuggestions(aiSuggestions);

        log.info("AI 생성 프로젝트 - title: {}, aiSuggestions: {}개",
                aiResult.getProjectTitle(), aiSuggestions.size());

        return aiResult;
    }

    // ────────────────────────────────────────────────
    // 목록 조회
    // ────────────────────────────────────────────────

    /**
     * 회원 ID로 내 프로젝트 목록 조회
     * - 각 프로젝트에 체크리스트 최대 2개를 포함하여 반환
     *
     * @param memberId 현재 로그인한 회원 ID
     * @return 프로젝트 목록 (List<ProjectResponseDTO>)
     */
    @Override
    public List<ProjectResponseDTO> getMyProjects(Long memberId) {
        return projectDAO.findAllByMemberId(memberId)
                .stream()
                .map(vo -> {
                    ProjectResponseDTO dto = toResponseDTO(vo);
                    // 체크리스트 최대 2개 포함
                    List<ChecklistResponseDTO> checklists = checklistDAO.findAllByProjectId(vo.getId())
                            .stream()
                            .limit(2)
                            .map(c -> {
                                ChecklistResponseDTO cdto = new ChecklistResponseDTO();
                                cdto.setId(c.getId());
                                cdto.setChecklistTitle(c.getChecklistTitle());
                                cdto.setChecklistPriority(c.getChecklistPriority());
                                cdto.setChecklistCompleted(c.getChecklistCompleted());
                                cdto.setChecklistFailed(c.getChecklistFailed());
                                return cdto;
                            })
                            .collect(Collectors.toList());
                    dto.setChecklists(checklists);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ────────────────────────────────────────────────
    // 단건 조회
    // ────────────────────────────────────────────────

    /**
     * 프로젝트 ID로 단건 조회
     * - 본인 소유 프로젝트가 아닐 경우 예외 발생
     *
     * @param projectId 조회할 프로젝트 ID
     * @param memberId  현재 로그인한 회원 ID
     * @return 프로젝트 상세 정보 (ProjectResponseDTO)
     */
    @Override
    public ProjectResponseDTO getProject(Long projectId, Long memberId) {
        ProjectVO projectVO = projectDAO.findById(projectId);
        if (projectVO == null || !projectVO.getMemberId().equals(memberId)) {
            throw new RuntimeException("프로젝트를 찾을 수 없습니다.");
        }
        return toResponseDTO(projectVO);
    }

    // ────────────────────────────────────────────────
    // 삭제
    // ────────────────────────────────────────────────

    /**
     * 프로젝트 삭제
     * - 본인 소유 프로젝트가 아닐 경우 예외 발생
     *
     * @param projectId 삭제할 프로젝트 ID
     * @param memberId  현재 로그인한 회원 ID
     */
    @Override
    public void deleteProject(Long projectId, Long memberId) {
        ProjectVO projectVO = projectDAO.findById(projectId);
        if (projectVO == null || !projectVO.getMemberId().equals(memberId)) {
            throw new RuntimeException("프로젝트를 찾을 수 없습니다.");
        }
        projectDAO.deleteById(projectId);
    }

    // ────────────────────────────────────────────────
    // 내부 클래스 — AI 응답 파싱용
    // ────────────────────────────────────────────────

    // OpenAI 응답을 담는 내부 클래스
    @lombok.Data
    private static class AiProjectResult {
        private String projectTitle;
        private String projectContent;
        private String projectStartDate;
        private String projectEndDate;
        private String progressDay;
        private List<ProjectResponseDTO.AiSuggestionItem> aiSuggestions;
    }

    // ProjectVO → ProjectResponseDTO 변환 헬퍼 메서드
    private ProjectResponseDTO toResponseDTO(ProjectVO vo) {
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(vo.getId());
        dto.setMemberId(vo.getMemberId());
        dto.setLogId(vo.getLogId());
        dto.setProjectTitle(vo.getProjectTitle());
        dto.setProjectContent(vo.getProjectContent());
        dto.setProjectStartDate(vo.getProjectStartDate());
        dto.setProjectEndDate(vo.getProjectEndDate());
        dto.setProgressDay(vo.getProgressDay());
        dto.setProjectCreatedAt(vo.getProjectCreatedAt());
        dto.setVisionTitle(vo.getVisionTitle());

        // AI 행동 추천 리스트로 변환
        List<ProjectResponseDTO.AiSuggestionItem> aiSuggestions = new ArrayList<>();
        if (vo.getAiSuggestion1Title() != null) {
            aiSuggestions.add(createAiSuggestion(vo.getAiSuggestion1Title(), vo.getAiSuggestion1Desc()));
            aiSuggestions.add(createAiSuggestion(vo.getAiSuggestion2Title(), vo.getAiSuggestion2Desc()));
            aiSuggestions.add(createAiSuggestion(vo.getAiSuggestion3Title(), vo.getAiSuggestion3Desc()));
            aiSuggestions.add(createAiSuggestion(vo.getAiSuggestion4Title(), vo.getAiSuggestion4Desc()));
        }
        dto.setAiSuggestions(aiSuggestions);

        return dto;
    }

    // AI 행동 추천 아이템 생성 헬퍼 메서드
    private ProjectResponseDTO.AiSuggestionItem createAiSuggestion(String title, String desc) {
        ProjectResponseDTO.AiSuggestionItem item = new ProjectResponseDTO.AiSuggestionItem();
        item.setTitle(title);
        item.setDesc(desc);
        return item;
    }

    /**
     * 프로젝트 수정
     * - 본인 소유 프로젝트가 아닐 경우 예외 발생
     * - 프로젝트 ID를 설정한 후 DB 업데이트
     *
     * @param projectId  수정할 프로젝트 ID
     * @param memberId   현재 로그인한 회원 ID
     * @param projectVO  수정할 프로젝트 정보 (ProjectVO)
     */
    @Override
    public void updateProject(Long projectId, Long memberId, ProjectVO projectVO) {
        ProjectVO existing = projectDAO.findById(projectId);
        if (existing == null || !existing.getMemberId().equals(memberId)) {
            throw new RuntimeException("프로젝트를 찾을 수 없습니다.");
        }
        projectVO.setId(projectId);
        projectDAO.updateProject(projectVO);
    }
}