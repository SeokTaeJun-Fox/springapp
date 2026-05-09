package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER          CONSTRAINT PK_REREPLY_REPORT PRIMARY KEY,
//REREPLY_REPORT_CONTENT      VARCHAR2(255)   NOT NULL,
//REREPLY_REPORT_CREATED_AT    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//REPORT_REASON_CATEGORY_ID   NUMBER          NOT NULL,
//MEMBER_ID                   NUMBER          NOT NULL,
//REREPLY_ID                  NUMBER          NOT NULL,
//CONSTRAINT FK_REREPLY_REPORT_REASON  FOREIGN KEY (REPORT_REASON_CATEGORY_ID) REFERENCES TBL_REPORT_REASON_CATEGORY(ID),
//CONSTRAINT FK_REREPLY_REPORT_MEMBER  FOREIGN KEY (MEMBER_ID)  REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_REREPLY_REPORT_REREPLY FOREIGN KEY (REREPLY_ID) REFERENCES TBL_REREPLY(ID)

@Component
@Data
public class RereplyReportVO {
    private Long id;
    private String rereplyReportContent;
    private String rereplyReportCreatedAt;
    private Long reportReasonCategoryId;
    private Long memberId;
    private Long rereplyId;
}
