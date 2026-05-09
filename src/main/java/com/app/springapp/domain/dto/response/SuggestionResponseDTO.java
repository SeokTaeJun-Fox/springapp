package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "체크리스트 제안 응답 DTO")
public class SuggestionResponseDTO {

    @Schema(description = "제안 작성자 회원 ID", example = "1")
    private Long memberId;

    @Schema(description = "제안 ID", example = "1")
    private Long id;

    @Schema(description = "제안 제목", example = "매일 운동 30분")
    private String suggestionTitle;

    @Schema(description = "체크리스트 추가 여부 (Y/N)", example = "N")
    private String isAddedList;

    @Schema(description = "체크리스트 ID", example = "1")
    private Long checklistId;

    @Schema(description = "프로젝트 ID", example = "1")
    private Long projectId;
}
