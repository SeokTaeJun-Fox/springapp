package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                  NUMBER         CONSTRAINT PK_GUESTBOOK PRIMARY KEY,
//GUESTBOOK_CONTENT   VARCHAR2(1000) NOT NULL,
//GUESTBOOK_CREATED_AT TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
//OWNER_MEMBER_ID     NUMBER         NOT NULL,
//WRITER_MEMBER_ID    NUMBER         NOT NULL,
//CONSTRAINT FK_GUESTBOOK_OWNER  FOREIGN KEY (OWNER_MEMBER_ID)  REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_GUESTBOOK_WRITER FOREIGN KEY (WRITER_MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class GuestbookVO {
    private Long   id;
    private String guestbookContent;
    private String guestbookCreatedAt;
    private Long   ownerMemberId;
    private Long   writerMemberId;
}