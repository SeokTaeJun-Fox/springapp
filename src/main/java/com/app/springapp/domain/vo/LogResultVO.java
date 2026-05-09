package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                              NUMBER          CONSTRAINT PK_LOG_RESULT PRIMARY KEY,
//LOG_ID                      NUMBER          NOT NULL,
//LOG_RESULT_FAILURE_TYPE         VARCHAR2(255)   NOT NULL,
//LOG_RESULT_FAILURE_TITLE        VARCHAR2(255)   NOT NULL,
//LOG_RESULT_FAILURE_DESC         VARCHAR2(1000)  NOT NULL,
//LOG_RESULT_ONE_LINE_SUMMARY     VARCHAR2(500)   NOT NULL,
//LOG_RESULT_ONE_LINE_SUB         VARCHAR2(500)   NOT NULL,
//LOG_RESULT_EXTERNAL_RATIO       NUMBER          NOT NULL,
//LOG_RESULT_INTERNAL_RATIO       NUMBER          NOT NULL,
//LOG_RESULT_FLOW_SITUATION       VARCHAR2(255)   NOT NULL,
//LOG_RESULT_FLOW_DECISION        VARCHAR2(255)   NOT NULL,
//LOG_RESULT_FLOW_RESULT          VARCHAR2(255)   NOT NULL,
//LOG_RESULT_LOG_KEY_DECISION     VARCHAR2(255)   NOT NULL,
//LOG_RESULT_LOG_EXTERNAL_FACTOR  VARCHAR2(255)   NOT NULL,
//LOG_RESULT_LOG_INTERNAL_FACTOR  VARCHAR2(255)   NOT NULL,
//CONSTRAINT FK_LOG_RESULT_LOG FOREIGN KEY (LOG_ID) REFERENCES TBL_LOG(ID)

@Component
@Data
public class LogResultVO {
    private Long id;
    private Long logId;
    private String logResultFailureType;
    private String logResultFailureTitle;
    private String logResultFailureDesc;
    private String logResultOneLineSummary;
    private String logResultOneLineSub;
    private int logResultExternalRatio;
    private int logResultInternalRatio;
    private String logResultFlowSituation;
    private String logResultFlowDecision;
    private String logResultFlowResult;
    private String logResultLogKeyDecision;
    private String logResultLogExternalFactor;
    private String logResultLogInternalFactor;
}
