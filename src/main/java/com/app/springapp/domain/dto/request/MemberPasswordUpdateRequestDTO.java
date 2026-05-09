package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "비밀번호 변경 요청 DTO")
public class MemberPasswordUpdateRequestDTO {

    @Schema(description = "현재 비밀번호", example = "oldPassword123!", required = true)
    private String currentPassword;

    @Schema(description = "새 비밀번호", example = "newPassword123!", required = true)
    private String newPassword;
}
