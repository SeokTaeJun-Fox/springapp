package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "소셜 로그인 요청 DTO")
public class MemberOauthRequestDTO {

    @Schema(description = "소셜 타입 (GOOGLE / NAVER / KAKAO)", example = "GOOGLE", required = true)
    private String socialType;

    @Schema(description = "소셜 ID", example = "google_123456", required = true)
    private String socialId;

    @Schema(description = "이메일", example = "user@gmail.com", required = true)
    private String memberEmail;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String memberName;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/image.jpg", required = false)
    private String memberProfileImageUrl;
}