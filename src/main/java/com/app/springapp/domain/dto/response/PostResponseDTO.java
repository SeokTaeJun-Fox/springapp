package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "커뮤니티 게시글 상세 응답 DTO")
public class PostResponseDTO {

    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "실패에서 배운 점을 공유합니다")
    private String postTitle;

    @Schema(description = "게시글 내용", example = "이번 프로젝트에서 가장 크게 느낀 점은...")
    private String postContent;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String postCreatedAt;

    @Schema(description = "조회 수", example = "100")
    private Integer postReadCount;

    @Schema(description = "작성자 ID", example = "1")
    private Long memberId;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String memberNickname;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/image.jpg")
    private String memberProfileImageUrl;

    @Schema(description = "카테고리 ID", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리명", example = "자유게시판")
    private String categoryName;

    @Schema(description = "좋아요 수", example = "25")
    private int likeCount;

    @Schema(description = "좋아요 클릭 여부", example = "1")
    private int isLike;
}
