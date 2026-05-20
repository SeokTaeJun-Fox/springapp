package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "AI 채팅 메시지 전송 요청 DTO")
public class AiChatRequestDTO {

    @Schema(description = "AI에게 보낼 메시지 내용", example = "이번 실패에서 어떤 점을 개선할 수 있을까요?", required = true)
    private String aiChatContent;

    @Schema(description = "메시지 역할 (user / assistant)", example = "user", required = false,
            allowableValues = {"user", "assistant"})
    private String aiChatRole;  // DEFAULT 'user'
}