package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_LOG PRIMARY KEY,
//LOG_TITLE       VARCHAR2(255)   NOT NULL,
//VISION_TITLE    VARCHAR2(255)   NOT NULL,
//LOG_CONTENT     VARCHAR2(255)   NOT NULL,
//LOG_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_ID       NUMBER          NOT NULL,
//CATEGORY_ID     NUMBER          NOT NULL,
//CONSTRAINT FK_LOG_MEMBER   FOREIGN KEY (MEMBER_ID)   REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_LOG_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORY(ID)

@Component
@Data
public class LogVO {
    private Long   id;
    private String logTitle;
    private String visionTitle;
    private String logContent;
    private String logCreatedAt;
    private int    logReadCount;
    private String logThumbnailUrl;
    private Long   memberId;
    private Long   categoryId;
}
