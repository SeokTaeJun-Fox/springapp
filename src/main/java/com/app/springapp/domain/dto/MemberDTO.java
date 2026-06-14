package com.app.springapp.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
public class MemberDTO {
    // 내부에서 사용 — 모든 필드 포함

    private Long id;
    private String memberEmail;
    private String memberPassword;  // 포함
    private String memberName;
    private String memberPhone;
    private String memberNickname;
    private String memberCreatedAt;
    private String memberProfileImageUrl;
    private String memberPicture;
    private String memberEmailVerifiedAt;
    private String memberPhoneVerifiedAt;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private String socialAccessToken;
    private String socialRefreshToken;
    private Long memberId;
    private Long memberLoginStreak;
    private LocalDateTime memberLastLoginAt;
    private String memberWithdrawnAt;


//    {
//        socialMemberProvider = "local";
//    }
}
