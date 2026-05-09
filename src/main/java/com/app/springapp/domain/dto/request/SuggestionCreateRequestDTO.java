package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "체크리스트 제안 생성 요청 DTO")
public class SuggestionCreateRequestDTO {

    @Schema(description = "프로젝트 ID", example = "1", required = true)
    private Long projectId;

    @Schema(description = "체크리스트 ID", example = "1", required = true)
    private Long checklistId;

    @Schema(description = "제안 제목", example = "매일 운동 30분", required = true)
    private String suggestionTitle;
}
