package com.app.springapp.domain.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LangchainResponseDTO {
    private String failureType;
    private String failureTitle;
    private String failureDesc;
    private String oneLineSummary;
    private String oneLineSub;
    private int externalRatio;
    private int internalRatio;
    private String flowSituation;
    private String flowDecision;
    private String flowResult;
    private String logKeyDecision;
    private String logExternalFactor;
    private String logInternalFactor;

    private Map<String, Integer> radarScores;
    private List<PatternDTO> patterns;
    private ActionPlansDTO actionPlans;

    @Data
    public static class PatternDTO {
        private String patternType;
        private String patternTitle;
        private String patternContent;
    }

    @Data
    public static class ActionPlansDTO {
        private List<TryPlanDTO> tryPlans;
        private List<ChangePlanDTO> changePlans;
    }

    @Data
    public static class TryPlanDTO {
        private String title;
        private String content;
    }

    @Data
    public static class ChangePlanDTO {
        private String title;
        private String currentPattern;
        private String currentContent;
        private String improvedPattern;
        private String improvedContent;
    }
}
