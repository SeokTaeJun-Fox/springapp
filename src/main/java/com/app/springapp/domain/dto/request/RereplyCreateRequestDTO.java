package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "대댓글 작성 요청 DTO")
public class RereplyCreateRequestDTO {

    @Schema(description = "댓글 ID", example = "1", required = true)
    private Long replyId;

    @Schema(description = "대댓글 내용", example = "저도 동의해요!", required = false)
    private String rereplyContent;
}
