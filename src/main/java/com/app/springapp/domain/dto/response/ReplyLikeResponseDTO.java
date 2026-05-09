package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 읽기 첫진입시 응답DTO")
public class ReplyLikeResponseDTO {

    @Schema(description = "좋아요 수", example = "3")
    private int likeCount;
}
