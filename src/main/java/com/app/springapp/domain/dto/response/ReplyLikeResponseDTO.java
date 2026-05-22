package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "댓글 좋아요 응답DTO")
public class ReplyLikeResponseDTO {

    @Schema(description = "좋아요 수", example = "3")
    private int likeCount;

    @Schema(description = "좋아요 클릭 여부", example = "1")
    private int isLiked;
}
