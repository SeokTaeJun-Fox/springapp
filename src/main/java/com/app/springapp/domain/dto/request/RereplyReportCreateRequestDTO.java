package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "대댓글 신고 요청 DTO")
public class RereplyReportCreateRequestDTO {

    @Schema(description = "신고할 대댓글 ID", example = "1", required = true)
    private Long rereplyId;

    @Schema(description = "신고 사유 카테고리 ID", example = "1", required = true)
    private Long reportReasonCategoryId;

    @Schema(description = "신고 내용", example = "스팸성 내용이 포함된 대댓글입니다.", required = true)
    private String rereplyReportContent;
}
