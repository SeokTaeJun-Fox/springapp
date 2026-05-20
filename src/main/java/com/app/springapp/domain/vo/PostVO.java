package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_POST PRIMARY KEY,
//POST_TITLE      VARCHAR2(255)   NOT NULL,
//POST_CONTENT    VARCHAR2(255)   NULL,
//POST_CREATED_AT  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//POST_READ_COUNT  NUMBER          DEFAULT 0 NOT NULL,
//MEMBER_ID       NUMBER          NOT NULL,
//CATEGORY_ID     NUMBER          NOT NULL,
//CONSTRAINT FK_POST_MEMBER   FOREIGN KEY (MEMBER_ID)   REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_POST_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES TBL_CATEGORY(ID)

@Component
@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private String postCreatedAt;
    private String postDeletedAt;
    private String postThumbnailUrl;
    private Integer postReadCount;
    private Long memberId;
    private Long categoryId;
}
