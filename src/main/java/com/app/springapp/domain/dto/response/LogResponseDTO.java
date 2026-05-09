package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "페일로그 상세 응답 DTO")
public class LogResponseDTO {

    @Schema(description = "로그 ID", example = "1")
    private Long id;

    @Schema(description = "로그 제목", example = "첫 번째 실패 회고")
    private String logTitle;

    @Schema(description = "비전 제목", example = "개발자로 성장하기")
    private String visionTitle;

    @Schema(description = "로그 내용", example = "이번 프로젝트에서 일정 관리에 실패했습니다.")
    private String logContent;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String logCreatedAt;

    @Schema(description = "작성자 ID", example = "1")
    private Long memberId;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String memberNickname;

    @Schema(description = "카테고리 ID", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리명", example = "개발")
    private String categoryName;

    @Schema(description = "좋아요 수", example = "10")
    private int likeCount;
}
