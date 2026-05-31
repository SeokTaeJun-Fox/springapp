package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "페일로그 목록 응답 DTO")
public class LogListResponseDTO {

    @Schema(description = "로그 ID", example = "1")
    private Long id;

    @Schema(description = "로그 제목", example = "첫 번째 실패 회고")
    private String logTitle;

    @Schema(description = "비전 제목", example = "개발자로 성장하기")
    private String visionTitle;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String logCreatedAt;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String memberNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://example.com/profile.png")
    private String memberProfileImageUrl;

    @Schema(description = "카테고리명", example = "개발")
    private String categoryName;

    @Schema(description = "조회수", example = "45")
    private int readCount;

    @Schema(description = "좋아요 수", example = "10")
    private int likeCount;

    @Schema(description = "썸네일 이미지 URL", example = "https://example.com/thumb.jpg")
    private String thumbnailUrl;

    @Schema(description = "로그 상태 (DRAFT / PUBLISHED)", example = "PUBLISHED",
            allowableValues = {"DRAFT", "PUBLISHED"})
    private String logStatus;

    @Schema(description = "진행률 (0~100)", example = "0")
    private int logProgress;

    @Schema(description = "현재 로그인된 사용자의 좋아요 여부", example = "true")
    private boolean isLiked;
}