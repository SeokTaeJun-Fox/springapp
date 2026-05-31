package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "프로젝트 생성 요청 DTO")
public class ProjectCreateRequestDTO {

    @Schema(description = "연결할 로그 ID", example = "1", required = true)
    private Long logId;

    @Schema(description = "사용자 세부 목표 (AI 프로젝트 생성에 활용)", example = "매일 30분씩 영어 회화 연습하기")
    private String userGoal;
}