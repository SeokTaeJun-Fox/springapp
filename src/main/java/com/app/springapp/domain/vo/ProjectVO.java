package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                  NUMBER          CONSTRAINT PK_PROJECT PRIMARY KEY,
//PROJECT_TITLE       VARCHAR2(255)   NOT NULL,
//PROJECT_CONTENT     VARCHAR2(255)   NOT NULL,
//PROJECT_START_DATE  TIMESTAMP       NOT NULL,
//PROJECT_END_DATE    TIMESTAMP       NOT NULL,
//PROGRESS_DAY        VARCHAR2(255)   NOT NULL,
//PROJECT_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_ID           NUMBER          NOT NULL,
//LOG_ID              NUMBER          NOT NULL,
//CONSTRAINT FK_PROJECT_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_PROJECT_LOG    FOREIGN KEY (LOG_ID)    REFERENCES TBL_LOG(ID)

@Component
@Data
public class ProjectVO {
    private Long id;
    private String projectTitle;
    private String projectContent;
    private String projectStartDate;
    private String projectEndDate;
    private String progressDay;
    private String projectCreatedAt;
    private Long memberId;
    private Long logId;
}
