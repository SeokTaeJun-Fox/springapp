package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER        CONSTRAINT PK_GUESTBOOK_REPLY PRIMARY KEY,
//GUESTBOOK_REPLY_CONTENT     VARCHAR2(500) NOT NULL,
//GUESTBOOK_REPLY_CREATED_AT   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
//GUESTBOOK_ID                NUMBER        NOT NULL,
//WRITER_MEMBER_ID            NUMBER        NOT NULL,
//CONSTRAINT FK_GB_REPLY_PARENT FOREIGN KEY (GUESTBOOK_ID)     REFERENCES TBL_GUESTBOOK(ID),
//CONSTRAINT FK_GB_REPLY_WRITER FOREIGN KEY (WRITER_MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class GuestbookReplyVO {
    private Long   id;
    private String guestbookReplyContent;
    private String guestbookReplyCreatedAt;
    private Long   guestbookId;
    private Long   writerMemberId;
}