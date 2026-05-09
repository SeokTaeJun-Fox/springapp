package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "프로젝트 생성 요청 DTO")
public class ProjectCreateRequestDTO {

    @Schema(description = "로그 ID", example = "1", required = true)
    private Long logId;

    @Schema(description = "프로젝트 제목", example = "2024 상반기 목표 달성 프로젝트", required = true)
    private String projectTitle;

    @Schema(description = "프로젝트 내용", example = "이전 실패를 개선하기 위한 실천 계획", required = true)
    private String projectContent;

    @Schema(description = "프로젝트 시작일 (yyyy-MM-dd)", example = "2024-01-01", required = true)
    private String projectStartDate;

    @Schema(description = "프로젝트 종료일 (yyyy-MM-dd)", example = "2024-06-30", required = true)
    private String projectEndDate;

    @Schema(description = "프로젝트 진행 경과일", example = "D+45", required = true)
    private String progressDay;
}
