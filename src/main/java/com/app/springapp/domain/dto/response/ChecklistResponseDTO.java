package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "체크리스트 항목 응답 DTO")
public class ChecklistResponseDTO {

    @Schema(description = "작성자 회원 ID", example = "1")
    private Long memberId;

    @Schema(description = "체크리스트 ID", example = "1")
    private Long id;

    @Schema(description = "프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "체크리스트 제목", example = "매일 30분 독서하기")
    private String checklistTitle;

    @Schema(description = "메모", example = "자기계발 서적 위주로")
    private String checklistMemo;

    @Schema(description = "완료 여부 (Y/N)", example = "N")
    private String checklistCompleted;

    @Schema(description = "실패 여부 (Y/N)", example = "N")
    private String checklistFailed;

    @Schema(description = "우선순위", example = "높음")
    private String checklistPriority;
}
