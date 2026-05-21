package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "페일로그 수정 요청 DTO")
public class LogUpdateRequestDTO {

    @Schema(description = "로그 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "로그 제목", example = "수정된 로그 제목", required = false)
    private String logTitle;

    @Schema(description = "비전 제목", example = "수정된 비전 제목", required = false)
    private String visionTitle;

    @Schema(description = "로그 내용", example = "수정된 로그 내용입니다.", required = false)
    private String logContent;

    @Schema(description = "카테고리 ID", example = "2", required = false)
    private Long categoryId;

    @Schema(description = "로그 상태 (DRAFT / PUBLISHED)", example = "DRAFT", required = false,
            allowableValues = {"DRAFT", "PUBLISHED"})
    private String logStatus;

    @Schema(description = "진행률 (0~100)", example = "50", required = false)
    private Integer logProgress;

    @Schema(description = "썸네일 이미지 URL", example = "https://example.com/thumb.jpg", required = false)
    private String logThumbnailUrl;
}