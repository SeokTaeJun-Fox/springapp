package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페일로그 좋아요 토글 요청 DTO")
public class LogLikeRequestDTO {

    @Schema(description = "로그 ID", example = "1")
    private Long logId;

    @Schema(description = "사용자 ID (세션에서 자동 주입됨)", example = "1", hidden = true)
    private Long memberId;
}
