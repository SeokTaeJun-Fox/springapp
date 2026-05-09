package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "액션 플랜 응답 DTO")
public class LogActionPlanResponseDTO {

    @Schema(description = "액션 플랜 ID", example = "1")
    private Long id;

    @Schema(description = "로그 결과 ID", example = "1")
    private Long logResultId;

    @Schema(description = "액션 플랜 유형 (TRY / CHANGE)", example = "TRY")
    private String logActionPlanType;

    @Schema(description = "액션 플랜 제목", example = "일일 TODO 리스트 작성")
    private String logActionPlanTitle;

    @Schema(description = "액션 플랜 내용", example = "매일 아침 당일 업무를 리스트로 정리한다.")
    private String logActionPlanContent;

    @Schema(description = "현재 패턴", example = "즉흥적으로 업무 진행")
    private String logActionPlanCurrentPattern;

    @Schema(description = "현재 패턴 내용", example = "급하게 처리하다 실수 반복")
    private String logActionPlanCurrentContent;

    @Schema(description = "개선 패턴", example = "계획 기반 업무 진행")
    private String logActionPlanImprovedPattern;

    @Schema(description = "개선 패턴 내용", example = "우선순위에 따라 단계적으로 처리")
    private String logActionPlanImprovedContent;
}
