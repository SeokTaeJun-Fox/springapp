package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "비밀번호 찾기 요청 DTO")
public class MemberFindPasswordRequestDTO {

    @Schema(description = "이메일", example = "user@example.com", required = true)
    private String memberEmail;

    @Schema(description = "새 비밀번호", example = "newPassword123!", required = true)
    private String newPassword;
}