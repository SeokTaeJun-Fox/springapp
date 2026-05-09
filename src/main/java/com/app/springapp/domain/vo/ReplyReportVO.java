package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER          CONSTRAINT PK_REPLY_REPORT PRIMARY KEY,
//REPLY_REPORT_CONTENT        VARCHAR2(255)   NOT NULL,
//REPLY_REPORT_CREATED_AT      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_ID                   NUMBER          NOT NULL,
//REPLY_ID                    NUMBER          NOT NULL,
//REPORT_REASON_CATEGORY_ID   NUMBER          NOT NULL,
//CONSTRAINT FK_REPLY_REPORT_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_REPLY_REPORT_REPLY  FOREIGN KEY (REPLY_ID)  REFERENCES TBL_REPLY(ID),
//CONSTRAINT FK_REPLY_REPORT_REASON FOREIGN KEY (REPORT_REASON_CATEGORY_ID) REFERENCES TBL_REPORT_REASON_CATEGORY(ID)

@Component
@Data
public class ReplyReportVO {
    private Long id;
    private String replyReportContent;
    private String replyReportCreatedAt;
    private Long memberId;
    private Long replyId;
    private Long reportReasonCategoryId;
}
