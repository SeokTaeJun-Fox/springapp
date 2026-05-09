package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "이메일 인증 확인 요청 DTO")
public class EmailVerificationConfirmRequestDTO {

    @Schema(description = "이메일", example = "user@example.com", required = true)
    private String memberEmail;

    @Schema(description = "인증 코드", example = "123456", required = true)
    private String code;

    @Schema(description = "인증 목적 (JOIN / FIND_ID / FIND_PW)", example = "JOIN", required = true)
    private String purpose;
}