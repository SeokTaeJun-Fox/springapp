package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "휴대폰 인증 발송 요청 DTO")
public class PhoneVerificationSendRequestDTO {

    @Schema(description = "전화번호", example = "010-1234-5678", required = true)
    private String memberPhone;
}