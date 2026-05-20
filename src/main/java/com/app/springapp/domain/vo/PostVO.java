package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                 NUMBER          CONSTRAINT PK_POST PRIMARY KEY,
//POST_TITLE         VARCHAR2(1500)  NOT NULL,
//POST_CONTENT       CLOB            NULL,
//POST_CREATED_AT    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//POST_READ_COUNT    NUMBER          DEFAULT 0 NOT NULL,
//MEMBER_ID          NUMBER          NOT NULL,
//CATEGORY_ID        NUMBER          NOT NULL,
//POST_DELETED_AT    TIMESTAMP       NULL,       -- 소프트 삭제 컬럼
//POST_THUMBNAIL_URL VARCHAR2(255)   NULL,       -- 썸네일 컬럼
//CONSTRAINT FK_POST_MEMBER   FOREIGN KEY (MEMBER_ID)   REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_POST_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORY(ID)

@Component
@Data
public class PostVO {
    private Long    id;
    private String  postTitle;
    private String  postContent;        // CLOB
    private String  postCreatedAt;
    private Integer postReadCount;
    private Long    memberId;
    private Long    categoryId;
    private String  postDeletedAt;      // 소프트 삭제용
    private String  postThumbnailUrl;   // 썸네일 URL
}