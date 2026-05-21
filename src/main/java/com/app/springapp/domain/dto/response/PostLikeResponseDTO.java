package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 좋아요 수 응답 DTO")
public class PostLikeResponseDTO {

    @Schema(description = "좋아요 수", example = "25")
    private int likeCount;

    @Schema(description = "좋아요 클릭 여부", example = "1")
    private int isLiked;
}
