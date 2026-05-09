package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID          NUMBER  CONSTRAINT PK_LOG_LIKE PRIMARY KEY,
//MEMBER_ID   NUMBER  NOT NULL,
//LOG_ID      NUMBER  NOT NULL,
//CONSTRAINT FK_LOG_LIKE_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID),
//CONSTRAINT FK_LOG_LIKE_LOG    FOREIGN KEY (LOG_ID)    REFERENCES TBL_LOG(ID)

@Component
@Data
public class LogLikeVO {
    private Long id;
    private Long memberId;
    private Long logId;
}
