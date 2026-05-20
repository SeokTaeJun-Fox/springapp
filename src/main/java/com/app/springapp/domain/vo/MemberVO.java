package com.app.springapp.domain.vo;

import com.app.springapp.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

//ID                          NUMBER          CONSTRAINT PK_MEMBER PRIMARY KEY,
//MEMBER_EMAIL                VARCHAR2(255)   NOT NULL,
//MEMBER_PASSWORD             VARCHAR2(255)   NULL,
//MEMBER_NAME                 VARCHAR2(255)   NOT NULL,
//MEMBER_PHONE                VARCHAR2(255)   NOT NULL,
//MEMBER_NICKNAME             VARCHAR2(255)   NOT NULL,
//MEMBER_CREATED_AT            TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_PROFILE_IMAGE_URL    VARCHAR2(255)   NULL,
//MEMBER_EMAIL_VERIFIED_AT    TIMESTAMP       NULL,
//MEMBER_PHONE_VERIFIED_AT    TIMESTAMP       NULL,
//MEMBER_LOGIN_STREAK         NUMBER          DEFAULT 0 NOT NULL,
//MEMBER_LAST_LOGIN_AT        TIMESTAMP       NULL

@Component
@Data
public class MemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickname;
    private String memberCreatedAt;
    private String memberProfileImageUrl;
    private String memberEmailVerifiedAt;
    private String memberPhoneVerifiedAt;
    private int memberLoginStreak;       // MEMBER_LOGIN_STREAK DEFAULT 0
    private String memberLastLoginAt;    // MEMBER_LAST_LOGIN_AT

    public static MemberVO from(MemberDTO memberDTO) {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(memberDTO.getId());
        memberVO.setMemberEmail(memberDTO.getMemberEmail());
        memberVO.setMemberPassword(memberDTO.getMemberPassword());
        memberVO.setMemberName(memberDTO.getMemberName());
        memberVO.setMemberPhone(memberDTO.getMemberPhone());
        memberVO.setMemberNickname(memberDTO.getMemberNickname() != null ? memberDTO.getMemberNickname() : "개복치 1단계");
        memberVO.setMemberCreatedAt(memberDTO.getMemberCreatedAt());
        memberVO.setMemberProfileImageUrl(memberDTO.getMemberProfileImageUrl() != null ? memberDTO.getMemberProfileImageUrl() : "https://testapp-codefuling.s3.ap-northeast-2.amazonaws.com/cat.jpg");
        memberVO.setMemberEmailVerifiedAt(memberDTO.getMemberEmailVerifiedAt());
        memberVO.setMemberPhoneVerifiedAt(memberDTO.getMemberPhoneVerifiedAt());
        return memberVO;
    }
}