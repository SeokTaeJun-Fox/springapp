package com.app.springapp.mapper;

import com.app.springapp.domain.vo.SocialMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialMemberMapper {
//    회원 추가
    public void insert(SocialMemberVO socialMemberVO);

//    회원 ID로 소셜 회원 정보 조회
    public SocialMemberVO selectByMemberId(Long memberId);

//    소셜 로그인 토큰 갱신
    public void updateTokens(SocialMemberVO socialMemberVO);

//    회원 탈퇴 시 소셜 식별자 무력화
    public void invalidateByMemberId(Long memberId);
}
