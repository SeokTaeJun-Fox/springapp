package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "레이더 차트 점수 저장 요청 DTO")
public class RadarScoreCreateRequestDTO {

    @Schema(description = "로그 결과 ID", example = "1", required = true)
    private Long logResultId;

    @Schema(description = "레이더 구성 요소명", example = "계획 수립", required = true)
    private String radarComponent;

    @Schema(description = "점수", example = "75", required = true)
    private int radarScore;
}
