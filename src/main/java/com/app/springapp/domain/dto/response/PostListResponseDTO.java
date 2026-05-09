package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "커뮤니티 게시글 목록 응답 DTO")
public class PostListResponseDTO {

    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "썸네 일", example = "http://")
    private String thumbnail;

    @Schema(description = "게시글 제목", example = "실패에서 배운 점을 공유합니다")
    private String postTitle;

    @Schema(description = "게시글 내용", example = "문제 정의를 다시 하고, 한가지 니즈에 집중하면서 서비스 구조를 정리했습니다.")
    private String postContent;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String postCreatedAt;

    @Schema(description = "조회 수", example = "100")
    private Integer postReadCount;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String memberNickname;

    @Schema(description = "카테고리명", example = "자유게시판")
    private String categoryName;

    @Schema(description = "좋아요 수", example = "25")
    private int likeCount;

    @Schema(description = "댓글 수", example = "5")
    private int replyCount;
}
