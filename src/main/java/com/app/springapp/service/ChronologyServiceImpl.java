package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;
import com.app.springapp.repository.ChronologyDAO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChronologyServiceImpl implements ChronologyService {

    private final ChronologyDAO chronologyDAO;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    // 비동기 AI 피드백 임시 저장소 (projectId → feedback)
    private final ConcurrentHashMap<Long, String> feedbackCache = new ConcurrentHashMap<>();

    @Override
    public ChronologyAnalysisResponseDTO getAnalysis(Long projectId) {
        String nickname = chronologyDAO.findNicknameByProjectId(projectId);
        if (nickname == null) nickname = "사용자";

        Long memberId = chronologyDAO.findMemberIdByProjectId(projectId);

        // 멤버의 전체 프로젝트 완료 체크리스트 수 (member 기준으로 avg와 스코프 통일)
        int totalChecklists   = memberId != null ? chronologyDAO.countCompletedChecklistsByMemberId(memberId) : 0;
        int avgUserChecklists = chronologyDAO.findAvgCompletedChecklists();
        int avgDays           = chronologyDAO.findAvgProjectDays();
        int projectCount      = chronologyDAO.findAvgProjectCount();

        // 퍼센타일: 나보다 완료 체크리스트 많은 멤버 수 / 전체 멤버 수
        int membersWithMore = chronologyDAO.countMembersWithMoreChecklists(totalChecklists);
        int totalMembers    = chronologyDAO.countMembersWithChecklists();
        double percentile   = totalMembers > 0
                ? Math.round((double) membersWithMore / totalMembers * 100 * 10) / 10.0
                : 50.0;

        // 멤버의 전체 프로젝트에서 가장 많이 반복된 체크리스트 Top 3
        List<Map<String, Object>> rawTop3 = memberId != null
                ? chronologyDAO.findTop3ChecklistsByMemberId(memberId)
                : Collections.emptyList();
        List<ChronologyAnalysisResponseDTO.ChecklistStat> top3 = rawTop3 == null
                ? Collections.emptyList()
                : rawTop3.stream()
                        .map(row -> {
                            String text  = String.valueOf(row.getOrDefault("CHECKLIST_TEXT", ""));
                            int count    = row.get("CHECKLIST_COUNT") != null
                                    ? ((Number) row.get("CHECKLIST_COUNT")).intValue() : 0;
                            return new ChronologyAnalysisResponseDTO.ChecklistStat(text, count);
                        })
                        .collect(Collectors.toList());

        return new ChronologyAnalysisResponseDTO(
                nickname, percentile, totalChecklists, avgUserChecklists, top3, avgDays, projectCount, null
        );
    }

    @Override
    @Async
    public void generateAiFeedbackAsync(Long projectId, ChronologyAnalysisResponseDTO dto) {
        // 이미 캐시에 있으면 재생성 안 함
        if (feedbackCache.containsKey(projectId)) return;

        log.info("[연대기] AI 피드백 비동기 생성 시작 - projectId: {}", projectId);
        String feedback = generateAiFeedback(dto);
        if (feedback != null) {
            feedbackCache.put(projectId, feedback);
            log.info("[연대기] AI 피드백 캐시 저장 완료 - projectId: {}", projectId);
        }
    }

    @Override
    public String getFeedback(Long projectId) {
        return feedbackCache.get(projectId);
    }

    private String generateAiFeedback(ChronologyAnalysisResponseDTO dto) {
        try {
            String top3Text = dto.getTop3Checklists().isEmpty() ? "데이터 없음" :
                    dto.getTop3Checklists().stream()
                            .map(c -> c.getText() + "(" + c.getCount() + "회)")
                            .collect(Collectors.joining(", "));

            String userPrompt = String.format(
                    "%s 님의 성과 분석 결과입니다.\n" +
                    "- 전체 이용자 중 상위 %.1f%%에 해당하는 체크리스트 달성률\n" +
                    "- 총 %d개의 체크리스트 완료 (이용자 평균 %d개)\n" +
                    "- 가장 많이 달성한 체크리스트 Top3: %s\n" +
                    "- 이용자 평균 목표달성 기간: %d일\n\n" +
                    "위 데이터를 바탕으로 이 사람의 성장 여정을 분석하고, 아래 항목을 모두 포함하여 " +
                    "따뜻하고 진정성 있는 코치의 말투로 약 2000자 분량의 한국어 피드백을 작성해주세요.\n\n" +
                    "1. 달성률과 체크리스트 수에 대한 구체적인 칭찬과 의미 부여\n" +
                    "2. Top3 체크리스트에서 보이는 이 사람의 행동 패턴과 강점 분석\n" +
                    "3. 앞으로의 성장을 위한 구체적인 조언과 방향 제시\n" +
                    "4. 마무리 응원 메시지\n\n" +
                    "수치를 자연스럽게 녹여 쓰되, 기계적이지 않고 코치가 직접 대화하는 것처럼 써주세요. " +
                    "각 항목은 자연스럽게 이어지도록 단락 구분하여 작성해주세요.",
                    dto.getNickname(), dto.getPercentile(),
                    dto.getTotalChecklists(), dto.getAvgUserChecklists(), top3Text,
                    dto.getAvgDays()
            );

            // 타임아웃 120초로 설정
            OpenAiService openAiService = new OpenAiService(apiKey, Duration.ofSeconds(120));
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("system",
                    "당신은 사용자의 목표 달성 데이터를 분석하고 성장을 격려하는 전문 코치입니다. " +
                    "요청한 분량(약 2000자)을 충실히 채워 상세하고 풍부한 피드백을 작성합니다."));
            messages.add(new ChatMessage("user", userPrompt));

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model(model)
                    .messages(messages)
                    .maxTokens(1500)
                    .build();

            return openAiService.createChatCompletion(request)
                    .getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            log.warn("[연대기] AI 피드백 생성 실패: {}", e.getMessage());
            return null;
        }
    }
}
