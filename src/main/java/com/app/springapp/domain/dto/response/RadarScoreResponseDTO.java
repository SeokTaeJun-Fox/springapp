package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "레이더 차트 점수 응답 DTO")
public class RadarScoreResponseDTO {

    @Schema(description = "레이더 점수 ID", example = "1")
    private Long id;

    @Schema(description = "로그 결과 ID", example = "1")
    private Long logResultId;

    @Schema(description = "레이더 구성 요소명", example = "계획 수립")
    private String radarComponent;

    @Schema(description = "점수", example = "75")
    private int radarScore;
}
