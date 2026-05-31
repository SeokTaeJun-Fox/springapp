package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "회원 정보 수정 요청 DTO")
public class MemberUpdateRequestDTO {

    @Schema(description = "닉네임", example = "새닉네임", required = false)
    private String memberNickname;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/image.jpg", required = false)
    private String memberProfileImageUrl;

    @Schema(description = "전화번호", example = "010-9876-5432", required = false)
    private String memberPhone;

    @Schema(description = "전화번호 인증 여부 (true 시 MEMBER_PHONE_VERIFIED_AT 갱신)", required = false)
    private Boolean verifyPhone;
}
