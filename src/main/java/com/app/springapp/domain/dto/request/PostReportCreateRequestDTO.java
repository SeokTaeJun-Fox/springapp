package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 신고 요청 DTO")
public class PostReportCreateRequestDTO {

    @Schema(description = "신고할 게시글 ID", example = "1", required = true)
    private Long postId;

    @Schema(description = "신고 사유 카테고리 ID", example = "2", required = true)
    private Long reportReasonCategoryId;

    @Schema(description = "신고 내용", example = "허위 정보가 포함된 게시글입니다.", required = true)
    private String postReportContent;
}
