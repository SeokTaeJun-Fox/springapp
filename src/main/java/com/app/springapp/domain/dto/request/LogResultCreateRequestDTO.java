package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "로그 분석 결과 저장 요청 DTO")
public class LogResultCreateRequestDTO {

    @Schema(description = "로그 ID", example = "1", required = true)
    private Long logId;

    @Schema(description = "실패 유형", example = "일정 관리 실패", required = true)
    private String logResultFailureType;

    @Schema(description = "실패 제목", example = "마감 기한 초과", required = true)
    private String logResultFailureTitle;

    @Schema(description = "실패 상세 설명", example = "초기 일정 산정이 너무 낙관적이었습니다.", required = true)
    private String logResultFailureDesc;

    @Schema(description = "한 줄 요약", example = "일정 관리 부재로 인한 마감 실패", required = true)
    private String logResultOneLineSummary;

    @Schema(description = "한 줄 보완 내용", example = "철저한 일정 계획 수립 필요", required = true)
    private String logResultOneLineSub;

    @Schema(description = "외부 요인 비율 (0~100)", example = "30", required = true)
    private int logResultExternalRatio;

    @Schema(description = "내부 요인 비율 (0~100)", example = "70", required = true)
    private int logResultInternalRatio;

    @Schema(description = "상황 흐름", example = "초반 요구사항 변경 잦음", required = true)
    private String logResultFlowSituation;

    @Schema(description = "의사결정 흐름", example = "리소스 배분을 잘못 판단", required = true)
    private String logResultFlowDecision;

    @Schema(description = "결과 흐름", example = "마감 3일 전 기능 미완성 확인", required = true)
    private String logResultFlowResult;

    @Schema(description = "핵심 의사결정", example = "초기 설계 단계 중요성 인식", required = true)
    private String logResultLogKeyDecision;

    @Schema(description = "외부 요인", example = "갑작스러운 요구사항 추가", required = true)
    private String logResultLogExternalFactor;

    @Schema(description = "내부 요인", example = "개인 역량 과대 평가", required = true)
    private String logResultLogInternalFactor;
}
