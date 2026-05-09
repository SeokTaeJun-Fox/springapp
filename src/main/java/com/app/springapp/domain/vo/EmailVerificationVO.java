package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailVerificationVO {
//    ID                              NUMBER          CONSTRAINT PK_EMAIL_VERIFICATION PRIMARY KEY,
//    MEMBER_ID                       NUMBER          NOT NULL,
//    EMAIL_VERIFICATION_EMAIL        VARCHAR2(255)   NOT NULL,
//    EMAIL_VERIFICATION_CODE         VARCHAR2(255)   NOT NULL,
//    EMAIL_VERIFICATION_PURPOSE      VARCHAR2(50)    NOT NULL,   -- JOIN / FIND_ID / FIND_PW
//    EMAIL_VERIFICATION_IS_VERIFIED  NUMBER(1)       DEFAULT 0,
//    EMAIL_VERIFICATION_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//    EMAIL_VERIFICATION_END_AT       TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//    EMAIL_VERIFICATION_VERIFIED_AT  TIMESTAMP       NULL,
//    CONSTRAINT FK_EMAIL_VERIFICATION_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

    private Long id;
    private Long memberId;
    private String emailVerificationEmail;
    private String emailVerificationCode;
    private String emailVerificationPurpose;
    private int emailVerificationIsVerified;
    private String emailVerificationCreatedAt;
    private String emailVerificationEndAt;
    private String emailVerificationVerifiedAt;
}
