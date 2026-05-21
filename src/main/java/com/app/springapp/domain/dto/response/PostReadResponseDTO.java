package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "게시글 읽기 첫진입시 응답DTO")
public class PostReadResponseDTO {

    @Schema(description = "작성자가 썼던 게시글수", example = "790")
    private Integer memberPostCount;

    @Schema(description = "작성자가 썼던 페일 로그수", example = "790")
    private Integer memberLogCount;

    @Schema(description = "작성자가 썼던 댓글수", example = "790")
    private Integer memberReplyCount;

    @Schema(description = "게시글 데이터")
    private PostResponseDTO post;

    @Schema(description = "답글 목록(대댓글 포함)")
    private List<PostReadReplyResponseDTO> replies;

    @Schema(description = "이미지 목록")
    private List<String> postPictures;

    @Schema(description = "이전 글")
    private PostBeforeResponseDTO beforePost;

    @Schema(description = "다음 글")
    private PostAfterResponseDTO afterPost;

    @Schema(description = "ai 맞춤게시글")
    private List<PostAiListResponseDTO> postAiList;
}
