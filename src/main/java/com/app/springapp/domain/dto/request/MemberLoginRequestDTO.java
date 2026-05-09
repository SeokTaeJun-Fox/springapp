package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "회원 로그인 요청 DTO")
public class MemberLoginRequestDTO {

    @Schema(description = "이메일", example = "user@example.com", required = true)
    private String memberEmail;

    @Schema(description = "비밀번호", example = "password123!", required = true)
    private String memberPassword;
}
