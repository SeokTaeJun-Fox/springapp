package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_REPLY PRIMARY KEY,
//REPLY_CONTENT   VARCHAR2(255)   NULL,
//REPLY_CREATED_AT TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//POST_ID         NUMBER          NOT NULL,
//MEMBER_ID       NUMBER          NOT NULL,
//CONSTRAINT FK_REPLY_POST   FOREIGN KEY (POST_ID)   REFERENCES TBL_POST(ID),
//CONSTRAINT FK_REPLY_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class ReplyVO {
    private Long id;
    private String replyContent;
    private String replyCreatedAt;
    private Long postId;
    private Long memberId;
}
