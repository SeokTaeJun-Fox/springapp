package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페일로그 좋아요 상태 DTO")
public class LogLikeResponseDTO {

    @Schema(description = "해당 로그의 총 좋아요 수", example = "10")
    private int likeCount;

    @Schema(description = "현재 로그인된 사용자의 좋아요 여부 (1: 누름, 0: 안 누름)", example = "1")
    private int isLiked;
}
