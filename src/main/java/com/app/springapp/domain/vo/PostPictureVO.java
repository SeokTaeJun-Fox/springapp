package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                      NUMBER          CONSTRAINT PK_POST_PICTURE PRIMARY KEY,
//POST_PICTURE_SEQUENCE   NUMBER          NOT NULL,
//POST_PICTURE_ADDRESS    VARCHAR2(255)   NOT NULL,
//POST_PICTURE_NAME       VARCHAR2(255)   NOT NULL,
//POST_ID                 NUMBER          NOT NULL,
//CONSTRAINT FK_POST_PICTURE_POST FOREIGN KEY (POST_ID) REFERENCES TBL_POST(ID)

@Component
@Data
public class PostPictureVO {
    private Long id;
    private Integer postPictureSequence;
    private String postPictureAddress;
    private String postPictureName;
    private Long postId;
}
