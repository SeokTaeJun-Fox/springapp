package com.app.springapp.repository;

import com.app.springapp.domain.vo.SocialMemberVO;
import com.app.springapp.mapper.SocialMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SocialMemberDAO {
    private final SocialMemberMapper socialMemberMapper;

//    소셜 회원 추가
    public void save(SocialMemberVO socialMemberVO){
        socialMemberMapper.insert(socialMemberVO);
    }
}
