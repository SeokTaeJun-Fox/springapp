package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "로그 패턴 응답 DTO")
public class LogPatternResponseDTO {

    @Schema(description = "패턴 ID", example = "1")
    private Long id;

    @Schema(description = "로그 결과 ID", example = "1")
    private Long logResultId;

    @Schema(description = "패턴 유형", example = "반복 실수")
    private String patternType;

    @Schema(description = "패턴 제목", example = "시간 관리 패턴")
    private String patternTitle;

    @Schema(description = "패턴 내용", example = "마감 직전에 집중력이 높아지는 패턴")
    private String patternContent;
}
