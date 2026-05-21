package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "AI 채팅 응답 DTO")
public class AiChatResponseDTO {

    @Schema(description = "채팅 ID", example = "1")
    private Long id;

    @Schema(description = "AI 응답 내용", example = "페일로그는 이런 서비스입니다!")
    private String aiChatContent;

    @Schema(description = "메시지 역할 (user / assistant)", example = "assistant",
            allowableValues = {"user", "assistant"})
    private String aiChatRole;

    @Schema(description = "응답 생성 일시", example = "2024-01-01T10:05:00")
    private String aiChatCreatedAt;

    @Schema(description = "회원 ID", example = "1")
    private Long memberId;
}