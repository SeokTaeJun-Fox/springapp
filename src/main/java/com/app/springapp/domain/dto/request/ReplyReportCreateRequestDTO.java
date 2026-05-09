package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "댓글 신고 요청 DTO")
public class ReplyReportCreateRequestDTO {

    @Schema(description = "신고할 댓글 ID", example = "1", required = true)
    private Long replyId;

    @Schema(description = "신고 사유 카테고리 ID", example = "3", required = true)
    private Long reportReasonCategoryId;

    @Schema(description = "신고 내용", example = "욕설이 포함된 댓글입니다.", required = true)
    private String replyReportContent;
}
