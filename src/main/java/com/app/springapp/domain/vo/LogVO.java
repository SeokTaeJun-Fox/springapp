package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                NUMBER          CONSTRAINT PK_LOG PRIMARY KEY,
//LOG_TITLE         VARCHAR2(255)   NOT NULL,
//VISION_TITLE      VARCHAR2(255)   NOT NULL,
//LOG_CONTENT       CLOB            NOT NULL,
//LOG_CREATED_AT     TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//LOG_READ_COUNT    NUMBER          DEFAULT 0 NOT NULL,
//LOG_THUMBNAIL_URL VARCHAR2(255)   NULL,
//MEMBER_ID         NUMBER          NOT NULL,
//CATEGORY_ID       NUMBER          NOT NULL,
//LOG_STATUS        VARCHAR2(20)    DEFAULT 'PUBLISHED' NOT NULL,  -- DRAFT / PUBLISHED
//LOG_PROGRESS      NUMBER          DEFAULT 0 NOT NULL,            -- 0~100
//LOG_DELETED_AT    TIMESTAMP       NULL,
//CONSTRAINT FK_LOG_MEMBER   FOREIGN KEY (MEMBER_ID)   REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_LOG_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORY(ID),
//CONSTRAINT CK_LOG_STATUS CHECK (LOG_STATUS IN ('DRAFT', 'PUBLISHED')),
//CONSTRAINT CK_LOG_PROGRESS CHECK (LOG_PROGRESS BETWEEN 0 AND 100)

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
    private String logStatus;       // DRAFT / PUBLISHED (DEFAULT 'PUBLISHED')
    private int    logProgress;     // 0~100 (DEFAULT 0)
    private String logDeletedAt;    // 소프트 삭제용
}