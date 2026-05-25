package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_SUGGESTION PRIMARY KEY,
//SUGGESTION_TITLE VARCHAR2(255)  NOT NULL,
//IS_ADDED_LIST   CHAR(1)         DEFAULT 'N',
//CHECKLIST_ID    NUMBER          NOT NULL,
//MEMBER_ID       NUMBER          NOT NULL,
//PROJECT_ID      NUMBER          NOT NULL,
//CONSTRAINT FK_SUGGESTION_CHECKLIST FOREIGN KEY (CHECKLIST_ID) REFERENCES TBL_CHECKLIST(ID),
//CONSTRAINT FK_SUGGESTION_MEMBER    FOREIGN KEY (MEMBER_ID)    REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_SUGGESTION_PROJECT   FOREIGN KEY (PROJECT_ID)   REFERENCES TBL_PROJECT(ID)

@Component
@Data
public class SuggestionVO {
    private Long id;
    private String suggestionTitle;
    private String isAddedList;
    private Long checklistId;
    private Long memberId;
    private Long projectId;
    private String memberNickname;
    private String memberProfileImageUrl;
}
