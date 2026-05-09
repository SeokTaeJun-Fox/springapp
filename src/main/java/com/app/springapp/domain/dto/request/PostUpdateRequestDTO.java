package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "커뮤니티 게시글 수정 요청 DTO")
public class PostUpdateRequestDTO {

    @Schema(description = "게시글 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "게시글 제목", example = "수정된 게시글 제목", required = true)
    private String postTitle;

    @Schema(description = "게시글 내용", example = "수정된 내용입니다.", required = false)
    private String postContent;

    @Schema(description = "카테고리 ID", example = "2", required = true)
    private Long categoryId;
}
