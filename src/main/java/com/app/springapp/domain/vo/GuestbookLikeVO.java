package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID           NUMBER CONSTRAINT PK_GUESTBOOK_LIKE PRIMARY KEY,
//GUESTBOOK_ID NUMBER NOT NULL,
//MEMBER_ID    NUMBER NOT NULL,
//CONSTRAINT FK_GB_LIKE_GB     FOREIGN KEY (GUESTBOOK_ID) REFERENCES TBL_GUESTBOOK(ID),
//CONSTRAINT FK_GB_LIKE_MEMBER FOREIGN KEY (MEMBER_ID)    REFERENCES TBL_MEMBER(ID),
//CONSTRAINT UQ_GUESTBOOK_LIKE UNIQUE (GUESTBOOK_ID, MEMBER_ID)

@Component
@Data
public class GuestbookLikeVO {
    private Long id;
    private Long guestbookId;
    private Long memberId;
}