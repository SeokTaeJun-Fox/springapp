package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "체크리스트 항목 생성 요청 DTO")
public class ChecklistCreateRequestDTO {

    @Schema(description = "프로젝트 ID", example = "1", required = true)
    private Long projectId;

    @Schema(description = "체크리스트 제목", example = "매일 30분 독서하기", required = true)
    private String checklistTitle;

    @Schema(description = "메모", example = "자기계발 서적 위주로", required = false)
    private String checklistMemo;

    @Schema(description = "우선순위 (높음, 중간, 낮음)", example = "높음", required = true)
    private String checklistPriority;
}
