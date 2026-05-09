package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID          NUMBER  CONSTRAINT PK_REPLY_LIKE PRIMARY KEY,
//REPLY_ID    NUMBER  NOT NULL,
//MEMBER_ID   NUMBER  NOT NULL,
//CONSTRAINT FK_REPLY_LIKE_REPLY  FOREIGN KEY (REPLY_ID)  REFERENCES TBL_REPLY(ID),
//CONSTRAINT FK_REPLY_LIKE_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class ReplyLikeVO {
    private Long id;
    private Long replyId;
    private Long memberId;
}
