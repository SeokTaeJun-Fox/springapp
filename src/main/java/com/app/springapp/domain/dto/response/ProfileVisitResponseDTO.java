package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "프로필 방문자 응답 DTO")
public class ProfileVisitResponseDTO {

    @Schema(description = "방문 기록 ID", example = "1")
    private Long id;

    @Schema(description = "프로필 주인 회원 ID", example = "1")
    private Long ownerMemberId;

    @Schema(description = "방문자 회원 ID", example = "2")
    private Long visitorMemberId;

    @Schema(description = "방문자 닉네임", example = "개발러")
    private String visitorNickname;

    @Schema(description = "방문자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String visitorProfileImageUrl;

    @Schema(description = "방문 일시", example = "2024-01-01T09:00:00")
    private String visitedAt;
}