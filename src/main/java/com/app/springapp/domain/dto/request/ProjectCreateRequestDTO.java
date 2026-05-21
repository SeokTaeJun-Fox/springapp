package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "프로젝트 생성 요청 DTO")
public class ProjectCreateRequestDTO {

    @Schema(description = "연결할 로그 ID", example = "1", required = true)
    private Long logId;

    // 아래 필드들은 AI가 생성하므로 요청 시 불필요 — 서비스 내부에서 채워짐
    // projectTitle, projectContent, progressDay, projectStartDate, projectEndDate
}