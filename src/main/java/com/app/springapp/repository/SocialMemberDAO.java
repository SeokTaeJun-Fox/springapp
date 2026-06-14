package com.app.springapp.repository;

import com.app.springapp.domain.vo.SocialMemberVO;
import com.app.springapp.mapper.SocialMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SocialMemberDAO {
    private final SocialMemberMapper socialMemberMapper;

//    소셜 회원 추가
    public void save(SocialMemberVO socialMemberVO){
        socialMemberMapper.insert(socialMemberVO);
    }

//    회원 ID로 소셜 회원 정보 조회
    public Optional<SocialMemberVO> findByMemberId(Long memberId){
        return Optional.ofNullable(socialMemberMapper.selectByMemberId(memberId));
    }

//    소셜 로그인 토큰 갱신
    public void updateTokens(SocialMemberVO socialMemberVO){
        socialMemberMapper.updateTokens(socialMemberVO);
    }

//    회원 탈퇴 시 소셜 식별자 무력화
    public void invalidateByMemberId(Long memberId){
        socialMemberMapper.invalidateByMemberId(memberId);
    }
}
