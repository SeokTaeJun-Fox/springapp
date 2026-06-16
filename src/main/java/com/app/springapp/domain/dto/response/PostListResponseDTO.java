package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "커뮤니티 게시글 목록 응답 DTO")
public class PostListResponseDTO {

    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "실패에서 배운 점을 공유합니다")
    private String postTitle;

    @Schema(description = "게시글 내용", example = "문제 정의를 다시 하고, 한가지 니즈에 집중하면서 서비스 구조를 정리했습니다.")
    private String postContent;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String postCreatedAt;

    @Schema(description = "조회 수", example = "100")
    private Integer postReadCount;

    @Schema(description = "작성자 id", example = "1")
    private Long memberId;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String memberNickname;

    @Schema(description = "작성자 프로필url", example = "http://")
    private String memberProfileImageUrl;


    @Schema(description = "카테고리id", example = "자유게시판")
    private int categoryId;

    @Schema(description = "좋아요 수", example = "25")
    private int likeCount;

    @Schema(description = "좋아요 클릭 여부",
            example = "1",
            allowableValues = {"0", "1"},
            type = "integer",
            format = "int32",
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = "x-enum-values", value = "0: false(좋아요 안누름), 1: true(좋아요 누름)")
                    })
            })
    private int isLiked;

    @Schema(description = "댓글 수", example = "5")
    private int replyCount;

    @Schema(description = "게시글 썸네일 URL", example = "http://")
    private String postThumbnailUrl;
}
