package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "페일로그 작성 요청 DTO")
public class LogCreateRequestDTO {

    @Schema(description = "로그 제목", example = "첫 번째 실패 회고", required = true)
    private String logTitle;

    @Schema(description = "비전 제목", example = "개발자로 성장하기", required = true)
    private String visionTitle;

    @Schema(description = "로그 내용", example = "이번 프로젝트에서 일정 관리에 실패했습니다.", required = true)
    private String logContent;

    @Schema(description = "카테고리 ID", example = "1", required = true)
    private Long categoryId;

    @Schema(description = "로그 상태 (DRAFT / PUBLISHED)", example = "PUBLISHED", required = false,
            allowableValues = {"DRAFT", "PUBLISHED"})
    private String logStatus;       // DEFAULT 'PUBLISHED'

    @Schema(description = "진행률 (0~100)", example = "0", required = false)
    private Integer logProgress;    // DEFAULT 0

    @Schema(description = "썸네일 이미지 URL", example = "https://example.com/thumb.jpg", required = false)
    private String logThumbnailUrl;
}