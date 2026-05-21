package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                  NUMBER    CONSTRAINT PK_PROFILE_VISIT PRIMARY KEY,
//OWNER_MEMBER_ID     NUMBER    NOT NULL,
//VISITOR_MEMBER_ID   NUMBER    NOT NULL,
//VISITED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//CONSTRAINT FK_VISIT_OWNER   FOREIGN KEY (OWNER_MEMBER_ID)   REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_VISIT_VISITOR FOREIGN KEY (VISITOR_MEMBER_ID) REFERENCES TBL_MEMBER(ID)
// ※ UNIQUE INDEX IDX_PROFILE_VISIT_DAILY (OWNER_MEMBER_ID, VISITOR_MEMBER_ID, TRUNC(VISITED_AT))

@Component
@Data
public class ProfileVisitVO {
    private Long   id;
    private Long   ownerMemberId;
    private Long   visitorMemberId;
    private String visitedAt;
}