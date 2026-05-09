package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_LOG_PATTERN PRIMARY KEY,
//PATTERN_TYPE    VARCHAR2(255)   NOT NULL,
//PATTERN_TITLE   VARCHAR2(255)   NOT NULL,
//PATTERN_CONTENT VARCHAR2(255)   NOT NULL,
//LOG_RESULT_ID   NUMBER          NOT NULL,
//CONSTRAINT FK_LOG_PATTERN_RESULT FOREIGN KEY (LOG_RESULT_ID) REFERENCES TBL_LOG_RESULT(ID)

@Component
@Data
public class LogPatternVO {
    private Long id;
    private String patternType;
    private String patternTitle;
    private String patternContent;
    private Long logResultId;
}
