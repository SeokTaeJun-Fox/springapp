package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                  NUMBER          CONSTRAINT PK_CHECKLIST PRIMARY KEY,
//CHECKLIST_TITLE     VARCHAR2(255)   NOT NULL,
//CHECKLIST_MEMO      VARCHAR2(255)   NULL,
//CHECKLIST_COMPLETED CHAR(1)         DEFAULT 'N',
//CHECKLIST_FAILED    CHAR(1)         DEFAULT 'N',
//CHECKLIST_PRIORITY  CHAR(2)         NOT NULL,
//PROJECT_ID          NUMBER          NOT NULL,
//MEMBER_ID           NUMBER          NOT NULL,
//CONSTRAINT FK_CHECKLIST_PROJECT FOREIGN KEY (PROJECT_ID) REFERENCES TBL_PROJECT(ID),
//CONSTRAINT FK_CHECKLIST_MEMBER  FOREIGN KEY (MEMBER_ID)  REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class ChecklistVO {
    private Long id;
    private String checklistTitle;
    private String checklistMemo;
    private String checklistCompleted;
    private String checklistFailed;
    private String checklistPriority;
    private Long projectId;
    private Long memberId;
}
