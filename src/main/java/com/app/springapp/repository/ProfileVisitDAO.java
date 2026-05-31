package com.app.springapp.repository;

import com.app.springapp.domain.vo.ProfileVisitVO;
import com.app.springapp.mapper.ProfileVisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileVisitDAO {

    private final ProfileVisitMapper profileVisitMapper;

    // 방문 기록 저장
    public void save(ProfileVisitVO profileVisitVO) {
        profileVisitMapper.insert(profileVisitVO);
    }

    // 오늘 방문자 수 조회
    public int countTodayVisitors(Long ownerId) {
        return profileVisitMapper.countTodayVisitors(ownerId);
    }
}
