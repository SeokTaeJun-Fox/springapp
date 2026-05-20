package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                      NUMBER          CONSTRAINT PK_PROJECT PRIMARY KEY,
//PROJECT_TITLE           VARCHAR2(255)   NOT NULL,
//PROJECT_CONTENT         VARCHAR2(255)   NOT NULL,
//PROJECT_START_DATE      TIMESTAMP       NOT NULL,
//PROJECT_END_DATE        TIMESTAMP       NOT NULL,
//PROGRESS_DAY            VARCHAR2(255)   NOT NULL,
//PROJECT_CREATED_AT      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_ID               NUMBER          NOT NULL,
//LOG_ID                  NUMBER          NOT NULL,
//AI_SUGGESTION_1_TITLE   VARCHAR2(255)   NULL,
//AI_SUGGESTION_1_DESC    VARCHAR2(500)   NULL,
//AI_SUGGESTION_2_TITLE   VARCHAR2(255)   NULL,
//AI_SUGGESTION_2_DESC    VARCHAR2(500)   NULL,
//AI_SUGGESTION_3_TITLE   VARCHAR2(255)   NULL,
//AI_SUGGESTION_3_DESC    VARCHAR2(500)   NULL,
//AI_SUGGESTION_4_TITLE   VARCHAR2(255)   NULL,
//AI_SUGGESTION_4_DESC    VARCHAR2(500)   NULL

@Component
@Data
public class ProjectVO {
    private Long   id;
    private String projectTitle;
    private String projectContent;
    private String projectStartDate;
    private String projectEndDate;
    private String progressDay;
    private String projectCreatedAt;
    private Long   memberId;
    private Long   logId;
    private String visionTitle;

    private String aiSuggestion1Title;
    private String aiSuggestion1Desc;
    private String aiSuggestion2Title;
    private String aiSuggestion2Desc;
    private String aiSuggestion3Title;
    private String aiSuggestion3Desc;
    private String aiSuggestion4Title;
    private String aiSuggestion4Desc;
}