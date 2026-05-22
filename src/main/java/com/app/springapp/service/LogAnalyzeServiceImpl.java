package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogAnalyzeRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LangchainResponseDTO;
import com.app.springapp.domain.vo.*;
import com.app.springapp.mapper.*;
import com.app.springapp.constant.LogPromptConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogAnalyzeServiceImpl implements LogAnalyzeService {

    private final LogMapper logMapper;
    private final LogResultMapper logResultMapper;
    private final RadarScoreMapper radarScoreMapper;
    private final LogPatternMapper logPatternMapper;
    private final LogActionPlanMapper logActionPlanMapper;
    private final ObjectMapper objectMapper;

    @Value("${langchain.server.url:http://localhost:8000}")
    private String langchainServerUrl;

    @Value("${openai.api-key}")
    private String openAiApiKey;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponseDTO analyzeLog(LogAnalyzeRequestDTO request, Long memberId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = langchainServerUrl + "/api/analyze";

        // 1. LangChain API 요청 파라미터 구성 (Python에 필요한 필드만 전송)
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", request.getTitle());
        payload.put("category", request.getCategory());
        payload.put("vision", request.getVision());
        payload.put("content", request.getContent());
        // style이 null이면 Python Pydantic이 422를 반환하므로 기본값 보장
        payload.put("style", request.getStyle() != null ? request.getStyle() : "objective");
        
        String pastLogsStr = "없음";
        if (request.getPastLogs() != null && !request.getPastLogs().isEmpty()) {
            pastLogsStr = String.join("\n", request.getPastLogs());
            payload.put("pastLogs", request.getPastLogs());
        } else {
            payload.put("pastLogs", java.util.Collections.emptyList());
        }

        log.info("Sending request to LangChain: {}", payload);

        LangchainResponseDTO aiResult = null;
        try {
            // 2. LangChain 호출 시도
            ResponseEntity<LangchainResponseDTO> response = restTemplate.postForEntity(url, payload, LangchainResponseDTO.class);
            aiResult = response.getBody();
            if (aiResult == null) {
                throw new RuntimeException("LangChain 응답이 비어있습니다.");
            }
        } catch (Exception ex) {
            log.warn("Python LangChain server failed or unreachable. Falling back to Spring Boot OpenAI API...", ex);
            // 3. Fallback: Spring Boot에서 직접 OpenAI 호출
            try {
                OpenAiService openAiService = new OpenAiService(openAiApiKey, java.time.Duration.ofSeconds(60));
                
                String guideline = LogPromptConstants.getStyleGuideline((String) payload.get("style"));
                String systemPrompt = LogPromptConstants.getSystemTemplate(guideline);
                String humanPrompt = LogPromptConstants.getHumanTemplate(
                    (String) payload.get("title"),
                    (String) payload.get("category"),
                    (String) payload.get("vision"),
                    (String) payload.get("content"),
                    pastLogsStr
                );

                List<ChatMessage> messages = new ArrayList<>();
                messages.add(new ChatMessage("system", systemPrompt));
                messages.add(new ChatMessage("user", humanPrompt));

                ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                        .model("gpt-4o-mini") // Python과 동일
                        .messages(messages)
                        .temperature(0.7)
                        .build();

                String aiResponseStr = openAiService.createChatCompletion(completionRequest)
                        .getChoices().get(0).getMessage().getContent();
                
                // 마크다운 블록 제거 (혹시 gpt-4o-mini가 json_object 모드 없이 마크다운으로 감싸서 응답할 경우 대비)
                if (aiResponseStr.startsWith("```json")) {
                    aiResponseStr = aiResponseStr.substring(7);
                }
                if (aiResponseStr.startsWith("```")) {
                    aiResponseStr = aiResponseStr.substring(3);
                }
                if (aiResponseStr.endsWith("```")) {
                    aiResponseStr = aiResponseStr.substring(0, aiResponseStr.length() - 3);
                }
                aiResponseStr = aiResponseStr.trim();
                
                aiResult = objectMapper.readValue(aiResponseStr, LangchainResponseDTO.class);

            } catch (Exception fallbackEx) {
                log.error("Fallback OpenAI API 연동 중 오류 발생", fallbackEx);
                throw new RuntimeException("로그 분석에 실패했습니다. (Fallback 서버 오류)", fallbackEx);
            }
        }

        try {
            // 4. 로그 마스터 테이블 (TBL_LOG) 저장 (상태는 PUBLISHED)
            // categoryId가 null이면 DB에서 ORA-01400 에러 발생하므로 방어 처리
            if (request.getCategoryId() == null) {
                throw new RuntimeException("카테고리가 선택되지 않았습니다. Step 1에서 카테고리를 선택해주세요.");
            }
            
            LogVO logVO = new LogVO();
            logVO.setLogTitle(request.getTitle());
            logVO.setVisionTitle(request.getVision());
            logVO.setLogContent(request.getContent());
            logVO.setLogThumbnailUrl(request.getLogThumbnailUrl());
            logVO.setCategoryId(request.getCategoryId());
            logVO.setLogStatus("PUBLISHED");
            logVO.setLogProgress(0); // TODO: 추후 프로그레스 로직
            logVO.setMemberId(memberId);
            
            logMapper.insert(logVO);
            Long savedLogId = logVO.getId();

            // 4. 분석 결과 마스터 (TBL_LOG_RESULT) 저장
            LogResultVO logResultVO = new LogResultVO();
            logResultVO.setLogId(savedLogId);
            logResultVO.setLogResultFailureType(aiResult.getFailureType());
            logResultVO.setLogResultFailureTitle(aiResult.getFailureTitle());
            logResultVO.setLogResultFailureDesc(aiResult.getFailureDesc());
            logResultVO.setLogResultOneLineSummary(aiResult.getOneLineSummary());
            logResultVO.setLogResultOneLineSub(aiResult.getOneLineSub());
            logResultVO.setLogResultExternalRatio(aiResult.getExternalRatio());
            logResultVO.setLogResultInternalRatio(aiResult.getInternalRatio());
            logResultVO.setLogResultFlowSituation(aiResult.getFlowSituation());
            logResultVO.setLogResultFlowDecision(aiResult.getFlowDecision());
            logResultVO.setLogResultFlowResult(aiResult.getFlowResult());
            logResultVO.setLogResultLogKeyDecision(aiResult.getLogKeyDecision());
            logResultVO.setLogResultLogExternalFactor(aiResult.getLogExternalFactor());
            logResultVO.setLogResultLogInternalFactor(aiResult.getLogInternalFactor());
            
            logResultMapper.insert(logResultVO);
            Long savedResultId = logResultVO.getId();

            // 5. 레이더 스코어 (TBL_RADAR_SCORE) 저장
            if (aiResult.getRadarScores() != null) {
                aiResult.getRadarScores().forEach((component, score) -> {
                    RadarScoreVO radarScoreVO = new RadarScoreVO();
                    radarScoreVO.setRadarComponent(component);
                    radarScoreVO.setRadarScore(score);
                    radarScoreVO.setLogResultId(savedResultId);
                    radarScoreMapper.insert(radarScoreVO);
                });
            }

            // 6. 실패 패턴 (TBL_LOG_PATTERN) 저장
            if (aiResult.getPatterns() != null) {
                for (LangchainResponseDTO.PatternDTO p : aiResult.getPatterns()) {
                    LogPatternVO patternVO = new LogPatternVO();
                    patternVO.setPatternType(p.getPatternType());
                    patternVO.setPatternTitle(p.getPatternTitle());
                    patternVO.setPatternContent(p.getPatternContent());
                    patternVO.setLogResultId(savedResultId);
                    logPatternMapper.insert(patternVO);
                }
            }

            // 7. 액션 플랜 (TBL_LOG_ACTION_PLAN) 저장
            if (aiResult.getActionPlans() != null) {
                // TRY 플랜 배열 저장
                if (aiResult.getActionPlans().getTryPlans() != null) {
                    for (LangchainResponseDTO.TryPlanDTO tp : aiResult.getActionPlans().getTryPlans()) {
                        LogActionPlanVO tryPlanVO = new LogActionPlanVO();
                        tryPlanVO.setLogActionPlanType("TRY");
                        tryPlanVO.setLogActionPlanTitle(tp.getTitle());
                        tryPlanVO.setLogActionPlanContent(tp.getContent());
                        tryPlanVO.setLogResultId(savedResultId);
                        logActionPlanMapper.insert(tryPlanVO);
                    }
                }

                // CHANGE 플랜 배열 저장
                if (aiResult.getActionPlans().getChangePlans() != null) {
                    for (LangchainResponseDTO.ChangePlanDTO cp : aiResult.getActionPlans().getChangePlans()) {
                        LogActionPlanVO changePlanVO = new LogActionPlanVO();
                        changePlanVO.setLogActionPlanType("CHANGE");
                        changePlanVO.setLogActionPlanTitle(cp.getTitle());
                        changePlanVO.setLogActionPlanCurrentPattern(cp.getCurrentPattern());
                        changePlanVO.setLogActionPlanCurrentContent(cp.getCurrentContent());
                        changePlanVO.setLogActionPlanImprovedPattern(cp.getImprovedPattern());
                        changePlanVO.setLogActionPlanImprovedContent(cp.getImprovedContent());
                        changePlanVO.setLogResultId(savedResultId);
                        logActionPlanMapper.insert(changePlanVO);
                    }
                }
            }

            return ApiResponseDTO.of(true, "로그 분석 및 저장 성공", savedLogId);

        } catch (Exception e) {
            log.error("LangChain 연동 중 오류 발생", e);
            throw new RuntimeException("로그 분석에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponseDTO getLogAnalyzeResult(Long logId) {
        // 1. 기본 로그 정보 조회
        com.app.springapp.domain.dto.response.LogResponseDTO logInfo = 
            logMapper.selectById(logId).orElseThrow(() -> new RuntimeException("로그를 찾을 수 없습니다."));

        // 2. 분석 결과 (TBL_LOG_RESULT) 조회
        LogResultVO aiResult = logResultMapper.findByLogId(logId);

        // 3. 응답 DTO 조립
        com.app.springapp.domain.dto.response.LogAnalyzeResultResponseDTO responseDto = 
            new com.app.springapp.domain.dto.response.LogAnalyzeResultResponseDTO();
        responseDto.setLogInfo(logInfo);
        responseDto.setAiResult(aiResult);

        if (aiResult != null) {
            responseDto.setRadarScores(radarScoreMapper.findByLogResultId(aiResult.getId()));
            responseDto.setLogPatterns(logPatternMapper.findByLogResultId(aiResult.getId()));
            responseDto.setLogActionPlans(logActionPlanMapper.findByLogResultId(aiResult.getId()));
        }

        return ApiResponseDTO.of(true, "로그 분석 결과 조회 성공", responseDto);
    }
}
