package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER          CONSTRAINT PK_POST_REPORT PRIMARY KEY,
//POST_REPORT_CONTENT         VARCHAR2(255)   NOT NULL,
//POST_REPORT_CREATED_AT       TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//REPORT_REASON_CATEGORY_ID   NUMBER          NOT NULL,
//POST_ID                     NUMBER          NOT NULL,
//MEMBER_ID                   NUMBER          NOT NULL,
//CONSTRAINT FK_POST_REPORT_REASON  FOREIGN KEY (REPORT_REASON_CATEGORY_ID) REFERENCES TBL_REPORT_REASON_CATEGORY(ID),
//CONSTRAINT FK_POST_REPORT_POST    FOREIGN KEY (POST_ID)   REFERENCES TBL_POST(ID),
//CONSTRAINT FK_POST_REPORT_MEMBER  FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class PostReportVO {
    private Long id;
    private String postReportContent;
    private String postReportCreatedAt;
    private Long reportReasonCategoryId;
    private Long postId;
    private Long memberId;
}
