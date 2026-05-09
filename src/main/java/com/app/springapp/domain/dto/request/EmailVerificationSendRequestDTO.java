package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "이메일 인증 발송 요청 DTO")
public class EmailVerificationSendRequestDTO {

    @Schema(description = "이메일", example = "user@example.com", required = true)
    private String memberEmail;

    @Schema(description = "인증 목적 (JOIN / FIND_ID / FIND_PW)", example = "JOIN", required = true)
    private String purpose;
}