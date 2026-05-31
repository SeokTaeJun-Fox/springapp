package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.AiChatRequestDTO;
import com.app.springapp.domain.dto.response.AiChatResponseDTO;
import com.app.springapp.service.AiChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "AI Chat", description = "AI 챗봇 API")
public class AiChatController {

    private final AiChatService aiChatService;

    @PostMapping("/chat")
    @Operation(summary = "AI 챗봇 메시지 전송", description = "사용자 메시지를 OpenAI에 전송하고 응답을 반환합니다.")
    public ResponseEntity<AiChatResponseDTO> chat(
            @RequestBody AiChatRequestDTO request,
            @AuthenticationPrincipal MemberDTO member) {

        Long memberId = member.getId();

        log.info("AI 챗봇 요청 - memberId: {}, message: {}", memberId, request.getAiChatContent());

        AiChatResponseDTO response = aiChatService.chat(request, memberId);

        return ResponseEntity.ok(response);
    }
}