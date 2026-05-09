package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "로그 분석 결과 응답 DTO")
public class LogResultResponseDTO {

    @Schema(description = "로그 결과 ID", example = "1")
    private Long id;

    @Schema(description = "로그 ID", example = "1")
    private Long logId;

    @Schema(description = "실패 유형", example = "일정 관리 실패")
    private String logResultFailureType;

    @Schema(description = "실패 제목", example = "마감 기한 초과")
    private String logResultFailureTitle;

    @Schema(description = "실패 상세 설명", example = "초기 일정 산정이 너무 낙관적이었습니다.")
    private String logResultFailureDesc;

    @Schema(description = "한 줄 요약", example = "일정 관리 부재로 인한 마감 실패")
    private String logResultOneLineSummary;

    @Schema(description = "한 줄 보완 내용", example = "철저한 일정 계획 수립 필요")
    private String logResultOneLineSub;

    @Schema(description = "외부 요인 비율", example = "30")
    private int logResultExternalRatio;

    @Schema(description = "내부 요인 비율", example = "70")
    private int logResultInternalRatio;

    @Schema(description = "상황 흐름", example = "초반 요구사항 변경 잦음")
    private String logResultFlowSituation;

    @Schema(description = "의사결정 흐름", example = "리소스 배분을 잘못 판단")
    private String logResultFlowDecision;

    @Schema(description = "결과 흐름", example = "마감 3일 전 기능 미완성 확인")
    private String logResultFlowResult;

    @Schema(description = "핵심 의사결정", example = "초기 설계 단계 중요성 인식")
    private String logResultLogKeyDecision;

    @Schema(description = "외부 요인", example = "갑작스러운 요구사항 추가")
    private String logResultLogExternalFactor;

    @Schema(description = "내부 요인", example = "개인 역량 과대 평가")
    private String logResultLogInternalFactor;
}
