package com.app.springapp.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "커뮤니티 게시글 열람 요청 DTO")
public class PostReadRequestDTO {

    @Schema(description = "게시글 열람 멤버id", example = "1", required = true)
    private Long memberId;

    @Schema(description = "열람할 게시글 id", example = "1", required = true)
    private Long postId;
}
