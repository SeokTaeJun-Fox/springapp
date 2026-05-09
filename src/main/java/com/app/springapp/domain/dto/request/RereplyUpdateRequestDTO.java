package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "대댓글 수정 요청 DTO")
public class RereplyUpdateRequestDTO {

    @Schema(description = "대댓글 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "수정할 대댓글 내용", example = "수정된 대댓글 내용입니다.", required = true)
    private String rereplyContent;
}
