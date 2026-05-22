package com.app.springapp.service;

import com.app.springapp.domain.dto.request.AiChatRequestDTO;
import com.app.springapp.domain.dto.response.AiChatResponseDTO;

// AI 챗봇 서비스 인터페이스
public interface AiChatService {

    // 사용자 메시지를 OpenAI에 전송하고 AI 응답을 반환
    AiChatResponseDTO chat(AiChatRequestDTO request, Long memberId);
}