package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class AiChatVO {
//    ID                  NUMBER          CONSTRAINT PK_AI_CHAT PRIMARY KEY,
//    AI_CHAT_CONTENT     VARCHAR2(255)   NOT NULL,
//    AI_CHAT_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//    MEMBER_ID           NUMBER          NOT NULL,
//    CONSTRAINT FK_AI_CHAT_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

    private Long id;
    private String aiChatContent;
    private String aiChatRole;      // 추가 필요
    private LocalDateTime aiChatCreateAt;
    private Long memberId;
}
