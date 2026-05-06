package com.app.springapp.mapper;

import com.app.springapp.domain.vo.SocialMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialMemberMapper {
//    회원 추가
    public void insert(SocialMemberVO socialMemberVO);
}
