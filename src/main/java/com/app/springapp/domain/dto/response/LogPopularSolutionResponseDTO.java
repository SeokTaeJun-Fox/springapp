package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "인기 솔루션 응답 DTO")
public class LogPopularSolutionResponseDTO {

    @Schema(description = "로그 ID")
    private Long logId;

    @Schema(description = "로그 제목")
    private String logTitle;

    @Schema(description = "멤버 닉네임")
    private String memberNickname;

    @Schema(description = "멤버 프로필 이미지 URL")
    private String memberProfileImageUrl;

    @Schema(description = "실패 유형 (외부 요인 40% 등)")
    private String logResultFailureType;

    @Schema(description = "실패 제목")
    private String logResultFailureTitle;

    @Schema(description = "실패 상세 설명")
    private String logResultFailureDesc;

    @Schema(description = "좋아요 수")
    private int likeCount;
}