package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "로그 패턴 저장 요청 DTO")
public class LogPatternCreateRequestDTO {

    @Schema(description = "로그 결과 ID", example = "1", required = true)
    private Long logResultId;

    @Schema(description = "패턴 유형", example = "반복 실수", required = true)
    private String patternType;

    @Schema(description = "패턴 제목", example = "시간 관리 패턴", required = true)
    private String patternTitle;

    @Schema(description = "패턴 내용", example = "마감 직전에 집중력이 높아지는 패턴", required = true)
    private String patternContent;
}
