package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;
import com.app.springapp.repository.ChronologyDAO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @Override
    public ChronologyAnalysisResponseDTO getAnalysis(Long projectId) {
        String nickname = chronologyDAO.findNicknameByProjectId(projectId);
        if (nickname == null) nickname = "사용자";

        int totalChecklists = chronologyDAO.countCompletedChecklistsByProjectId(projectId);
        int avgUserChecklists = chronologyDAO.findAvgCompletedChecklists();
        int avgDays = chronologyDAO.findAvgProjectDays();
        int projectCount = chronologyDAO.findAvgProjectCount();

        int membersWithMore = chronologyDAO.countMembersWithMoreChecklists(totalChecklists);
        int totalMembers = chronologyDAO.countMembersWithChecklists();
        double percentile = totalMembers > 0
                ? Math.round((double) membersWithMore / totalMembers * 100 * 10) / 10.0
                : 50.0;

        List<Map<String, Object>> rawTop3 = chronologyDAO.findTop3ChecklistsByProjectId(projectId);
        List<ChronologyAnalysisResponseDTO.ChecklistStat> top3 = rawTop3 == null
                ? Collections.emptyList()
                : rawTop3.stream()
                        .map(row -> {
                            String text = String.valueOf(row.getOrDefault("CHECKLIST_TEXT", ""));
                            int count = row.get("CHECKLIST_COUNT") != null
                                    ? ((Number) row.get("CHECKLIST_COUNT")).intValue() : 0;
                            return new ChronologyAnalysisResponseDTO.ChecklistStat(text, count);
                        })
                        .collect(Collectors.toList());

        ChronologyAnalysisResponseDTO dto = new ChronologyAnalysisResponseDTO(
                nickname, percentile, totalChecklists, avgUserChecklists, top3, avgDays, projectCount, null
        );

        dto.setAiFeedback(generateAiFeedback(dto));
        return dto;
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
                    "- 가장 많이 달성한 체크리스트 Top3: %s\n\n" +
                    "이 데이터를 바탕으로 이 사람의 성장 여정을 칭찬하고 앞으로의 방향을 격려하는 피드백을 " +
                    "따뜻하고 진정성 있는 말투로 2~3문장 한국어로 작성해주세요. " +
                    "수치를 직접 언급하되, 기계적이지 않고 코치가 직접 말하는 것처럼 써주세요.",
                    dto.getNickname(), dto.getPercentile(),
                    dto.getTotalChecklists(), dto.getAvgUserChecklists(), top3Text
            );

            OpenAiService openAiService = new OpenAiService(apiKey);
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("system",
                    "당신은 사용자의 목표 달성 데이터를 분석하고 성장을 격려하는 전문 코치입니다."));
            messages.add(new ChatMessage("user", userPrompt));

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model(model)
                    .messages(messages)
                    .maxTokens(300)
                    .build();

            return openAiService.createChatCompletion(request)
                    .getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            log.warn("[연대기] AI 피드백 생성 실패: {}", e.getMessage());
            return null;
        }
    }
}
