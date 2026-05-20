package com.app.springapp.service;

import com.app.springapp.domain.dto.request.AiChatRequestDTO;
import com.app.springapp.domain.dto.response.AiChatResponseDTO;

public interface AiChatService {
    AiChatResponseDTO chat(AiChatRequestDTO request, Long memberId);
}