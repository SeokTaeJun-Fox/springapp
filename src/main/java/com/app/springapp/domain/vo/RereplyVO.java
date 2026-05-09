package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                  NUMBER          CONSTRAINT PK_REREPLY PRIMARY KEY,
//REREPLY_CONTENT     VARCHAR2(255)   NULL,
//REREPLY_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//REPLY_ID            NUMBER          NOT NULL,
//MEMBER_ID           NUMBER          NOT NULL,
//CONSTRAINT FK_REREPLY_REPLY  FOREIGN KEY (REPLY_ID)  REFERENCES TBL_REPLY(ID),
//CONSTRAINT FK_REREPLY_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class RereplyVO {
    private Long id;
    private String rereplyContent;
    private String rereplyCreatedAt;
    private Long replyId;
    private Long memberId;
}
