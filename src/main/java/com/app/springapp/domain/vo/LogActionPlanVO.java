package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                              NUMBER          CONSTRAINT PK_LOG_ACTION_PLAN PRIMARY KEY,
//LOG_ACTION_PLAN_TYPE            VARCHAR2(20)    NOT NULL,   -- TRY / CHANGE
//LOG_ACTION_PLAN_TITLE           VARCHAR2(255)   NOT NULL,
//LOG_ACTION_PLAN_CONTENT         VARCHAR2(255)   NULL,
//LOG_ACTION_PLAN_CURRENT_PATTERN VARCHAR2(255)   NULL,
//LOG_ACTION_PLAN_CURRENT_CONTENT VARCHAR2(255)   NULL,
//LOG_ACTION_PLAN_IMPROVED_PATTERN VARCHAR2(255)  NULL,
//LOG_ACTION_PLAN_IMPROVED_CONTENT VARCHAR2(255)  NULL,
//LOG_RESULT_ID                   NUMBER          NOT NULL,
//CONSTRAINT FK_LOG_ACTION_PLAN_RESULT FOREIGN KEY (LOG_RESULT_ID) REFERENCES TBL_LOG_RESULT(ID),
//CONSTRAINT CK_ACTION_PLAN_TYPE CHECK (LOG_ACTION_PLAN_TYPE IN ('TRY','CHANGE'))

@Component
@Data
public class LogActionPlanVO {
    private Long id;
    private String logActionPlanType;
    private String logActionPlanTitle;
    private String logActionPlanContent;
    private String logActionPlanCurrentPattern;
    private String logActionPlanCurrentContent;
    private String logActionPlanImprovedPattern;
    private String logActionPlanImprovedContent;
    private Long logResultId;
}
