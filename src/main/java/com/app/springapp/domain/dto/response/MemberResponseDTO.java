package com.app.springapp.domain.dto.response;

import com.app.springapp.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class MemberResponseDTO {
    private Long id;
    private String memberEmail;
    private String memberPicture;
    private String memberName;
    private String memberNickname;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private Long memberId;
    private Long memberLoginStreak;
    private LocalDateTime memberLastLoginAt;
    private String memberPhone;
    private String memberPhoneVerifiedAt;

    public static MemberResponseDTO from(MemberDTO memberDTO){
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(memberDTO.getId());
        memberResponseDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberResponseDTO.setMemberPicture(memberDTO.getMemberPicture());
        memberResponseDTO.setMemberName(memberDTO.getMemberName());
        memberResponseDTO.setMemberNickname(memberDTO.getMemberNickname());
        memberResponseDTO.setSocialMemberProviderId(memberDTO.getSocialMemberProviderId());
        memberResponseDTO.setSocialMemberProvider(memberDTO.getSocialMemberProvider());
        memberResponseDTO.setMemberId(memberDTO.getMemberId());
        memberResponseDTO.setMemberLoginStreak(memberDTO.getMemberLoginStreak());
        memberResponseDTO.setMemberLastLoginAt(memberDTO.getMemberLastLoginAt());
        memberResponseDTO.setMemberPhone(memberDTO.getMemberPhone());
        memberResponseDTO.setMemberPhoneVerifiedAt(memberDTO.getMemberPhoneVerifiedAt());

        return memberResponseDTO;
    }

}
