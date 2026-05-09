package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PhoneVerificationVO {
//    ID                              NUMBER          CONSTRAINT PK_PHONE_VERIFICATION PRIMARY KEY,
//    MEMBER_ID                       NUMBER          NOT NULL,
//    PHONE_VERIFICATION_PHONE_NUMBER VARCHAR2(255)   NOT NULL,
//    PHONE_VERIFICATION_CODE         VARCHAR2(255)   NOT NULL,
//    PHONE_VERIFICATION_IS_VERIFIED  NUMBER(1)       DEFAULT 0,
//    PHONE_VERIFICATION_CREATED_AT   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//    PHONE_VERIFICATION_END_AT       TIMESTAMP       NULL,
//    PHONE_VERIFICATION_VERIFIED_AT  TIMESTAMP       NULL,
//    CONSTRAINT FK_PHONE_VERIFICATION_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES TBL_MEMBER(ID)

    private Long id;
    private Long memberId;
    private String phoneVerificationPhoneNumber;
    private String phoneVerificationCode;
    private int phoneVerificationIsVerified;
    private String phoneVerificationCreatedAt;
    private String phoneVerificationEndAt;
    private String phoneVerificationVerifiedAt;
}
