package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER          CONSTRAINT PK_REPORT_REASON_CATEGORY PRIMARY KEY,
//REPORT_REASON_CATEGORY_NAME VARCHAR2(255)   NOT NULL

@Component
@Data
public class ReportReasonCategoryVO {
    private Long id;
    private String reportReasonCategoryName;
}
