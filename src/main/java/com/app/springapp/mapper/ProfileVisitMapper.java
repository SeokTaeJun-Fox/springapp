package com.app.springapp.mapper;

import com.app.springapp.domain.vo.ProfileVisitVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileVisitMapper {
    // 방문 기록 저장
    void insert(ProfileVisitVO profileVisitVO);
    // 오늘 방문자 수 조회
    int countTodayVisitors(Long ownerId);
}
