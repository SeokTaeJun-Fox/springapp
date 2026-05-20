package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                 NUMBER CONSTRAINT PK_GB_REPLY_LIKE PRIMARY KEY,
//GUESTBOOK_REPLY_ID NUMBER NOT NULL,
//MEMBER_ID          NUMBER NOT NULL,
//CONSTRAINT FK_GB_REPLY_LIKE_REPLY  FOREIGN KEY (GUESTBOOK_REPLY_ID) REFERENCES TBL_GUESTBOOK_REPLY(ID),
//CONSTRAINT FK_GB_REPLY_LIKE_MEMBER FOREIGN KEY (MEMBER_ID)          REFERENCES TBL_MEMBER(ID),
//CONSTRAINT UQ_GB_REPLY_LIKE UNIQUE (GUESTBOOK_REPLY_ID, MEMBER_ID)

@Component
@Data
public class GuestbookReplyLikeVO {
    private Long id;
    private Long guestbookReplyId;
    private Long memberId;
}