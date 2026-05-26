package com.app.springapp.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronologyAnalysisResponseDTO {
    private String nickname;
    private double percentile;
    private int totalChecklists;
    private int avgUserChecklists;
    private List<ChecklistStat> top3Checklists;
    private int avgDays;
    private int projectCount;
    private String aiFeedback;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChecklistStat {
        private String text;
        private int count;
    }
}
