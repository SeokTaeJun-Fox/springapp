package com.app.springapp.domain.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class LogAnalyzeRequestDTO {
    private Long logId;             // (선택) 업데이트할 기존 DRAFT 로그 ID
    private String title;           // 로그 제목 (화면의 title)
    private Long categoryId;        // 카테고리 ID
    private String category;        // 카테고리 이름
    private String vision;          // 비전 제목
    private String content;         // 로그 본문 (logContent)
    private String style;           // 분석 스타일 (warm, cold, objective)
    private List<String> pastLogs;  // 과거 로그 텍스트 모음
    private String logThumbnailUrl; // 썸네일
}
