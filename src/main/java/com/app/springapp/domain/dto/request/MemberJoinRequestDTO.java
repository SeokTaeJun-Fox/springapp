package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "회원 가입 요청 DTO")
public class MemberJoinRequestDTO {

    @Schema(description = "이메일", example = "user@example.com", required = true)
    private String memberEmail;

    @Schema(description = "비밀번호", example = "password123!", required = true)
    private String memberPassword;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String memberName;

    @Schema(description = "전화번호", example = "010-1234-5678", required = true)
    private String memberPhone;

    @Schema(description = "닉네임", example = "길동이", required = false)
    private String memberNickname;
}