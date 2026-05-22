package com.app.springapp.service;

import com.app.springapp.domain.dto.request.AiChatRequestDTO;
import com.app.springapp.domain.dto.response.AiChatResponseDTO;
import com.app.springapp.domain.vo.AiChatVO;
import com.app.springapp.mapper.AiChatMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// AI 챗봇 서비스 구현체 - 대화 히스토리 기반으로 OpenAI API를 호출하고 응답을 반환
@Service
@RequiredArgsConstructor
public class AiChatServiceImpl implements AiChatService {

    private final AiChatMapper aiChatMapper;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    /**
     * 사용자 메시지를 OpenAI에 전송하고 AI 응답을 반환
     * - 이전 대화 히스토리를 함께 전송하여 문맥을 유지
     * - 사용자 메시지와 AI 응답을 DB에 저장
     *
     * @param request  사용자가 입력한 채팅 메시지 (AiChatRequestDTO)
     * @param memberId 현재 로그인한 회원 ID
     * @return AI가 생성한 응답 메시지 (AiChatResponseDTO)
     */
    @Override
    public AiChatResponseDTO chat(AiChatRequestDTO request, Long memberId) {

        // 1. 이전 대화 히스토리 조회
        List<AiChatVO> history = aiChatMapper.selectChatHistory(memberId);

        // 2. OpenAI 메시지 리스트 구성
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("system",
                "당신은 페일로그 서비스의 AI 어시스턴트입니다. 사용자의 실패 경험을 분석하고 성장을 도와주세요."));

        for (AiChatVO chat : history) {
            messages.add(new ChatMessage(chat.getAiChatRole(), chat.getAiChatContent()));
        }
        messages.add(new ChatMessage("user", request.getAiChatContent()));

        // 3. OpenAI API 호출
        OpenAiService openAiService = new OpenAiService(apiKey);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model(model)
                .messages(messages)
                .build();

        String aiResponse = openAiService.createChatCompletion(completionRequest)
                .getChoices().get(0).getMessage().getContent();

        // 4. DB 저장 (user 메시지 + assistant 응답)
        aiChatMapper.insertAiChat(memberId, request.getAiChatContent(), "user");
        aiChatMapper.insertAiChat(memberId, aiResponse, "assistant");

        // 5. 응답 반환
        AiChatResponseDTO response = new AiChatResponseDTO();
        response.setAiChatContent(aiResponse);
        response.setMemberId(memberId);

        return response;
    }
}