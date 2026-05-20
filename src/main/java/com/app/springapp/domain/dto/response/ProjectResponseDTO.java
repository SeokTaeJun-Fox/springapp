package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "프로젝트 응답 DTO")
public class ProjectResponseDTO {

    @Schema(description = "작성자 회원 ID", example = "1")
    private Long memberId;

    @Schema(description = "프로젝트 ID", example = "1")
    private Long id;

    @Schema(description = "연결된 로그 ID", example = "1")
    private Long logId;

    @Schema(description = "연결된 로그의 비전 제목", example = "개발자로 성장하기")
    private String visionTitle;

    @Schema(description = "프로젝트 제목", example = "2024 상반기 목표 달성 프로젝트")
    private String projectTitle;

    @Schema(description = "프로젝트 내용", example = "이전 실패를 개선하기 위한 실천 계획")
    private String projectContent;

    @Schema(description = "프로젝트 시작일", example = "2024-01-01")
    private String projectStartDate;

    @Schema(description = "프로젝트 종료일", example = "2024-06-30")
    private String projectEndDate;

    @Schema(description = "프로젝트 진행 경과일", example = "D+45")
    private String progressDay;

    @Schema(description = "생성 일시", example = "2024-01-01 09:00:00")
    private String projectCreatedAt;

    @Schema(description = "체크리스트 목록")
    private List<ChecklistResponseDTO> checklists;

    @Schema(description = "제안 목록")
    private List<SuggestionResponseDTO> suggestions;

    // ── AI 행동 추천 목록 (DB 저장 없이 생성 시점에만 전달) ──
    @Schema(description = "AI 행동 추천 목록")
    private List<AiSuggestionItem> aiSuggestions;

    @lombok.Data
    public static class AiSuggestionItem {
        @Schema(description = "행동 추천 제목", example = "말하기 전 3초 생각하기")
        private String title;

        @Schema(description = "행동 추천 설명", example = "감정적 반응을 줄이기 위해 말하기 전 잠시 멈추는 습관을 만듭니다.")
        private String desc;
    }
}