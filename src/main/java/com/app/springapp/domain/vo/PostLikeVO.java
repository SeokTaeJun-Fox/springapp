package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID          NUMBER  CONSTRAINT PK_POST_LIKE PRIMARY KEY,
//POST_ID     NUMBER  NOT NULL,
//MEMBER_ID   NUMBER  NOT NULL,
//CONSTRAINT FK_POST_LIKE_POST   FOREIGN KEY (POST_ID)   REFERENCES TBL_POST(ID),
//CONSTRAINT FK_POST_LIKE_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

@Component
@Data
public class PostLikeVO {
    private Long id;
    private Long postId;
    private Long memberId;
}
