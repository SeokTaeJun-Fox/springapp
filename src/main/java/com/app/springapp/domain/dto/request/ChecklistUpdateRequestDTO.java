package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "체크리스트 항목 수정 요청 DTO")
public class ChecklistUpdateRequestDTO {

    @Schema(description = "체크리스트 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "체크리스트 제목", example = "수정된 체크리스트 제목", required = false)
    private String checklistTitle;

    @Schema(description = "메모", example = "수정된 메모", required = false)
    private String checklistMemo;

    @Schema(description = "완료 여부 (Y/N)", example = "Y", required = false)
    private String checklistCompleted;

    @Schema(description = "실패 여부 (Y/N)", example = "N", required = false)
    private String checklistFailed;

    @Schema(description = "우선순위", example = "중간", required = false)
    private String checklistPriority;
}
