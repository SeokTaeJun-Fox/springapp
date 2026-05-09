package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID                      NUMBER          CONSTRAINT PK_USER_OAUTH PRIMARY KEY,
//MEMBER_ID               NUMBER          NOT NULL,
//USER_OAUTH_SOCIAL_TYPE  VARCHAR2(20)    NOT NULL,   -- GOOGLE / NAVER / KAKAO
//USER_OAUTH_SOCIAL_ID    VARCHAR2(255)   NOT NULL,
//USER_OAUTH_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,

@Component
@Data
public class MemberOauthVO {
    private Long id;
    private Long memberId;
    private String memberOauthSocialType;
    private String memberOauthSocialId;
    private String memberOauthCreatedAt;
}
