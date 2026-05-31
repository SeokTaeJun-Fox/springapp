package com.app.springapp.service;

import com.app.springapp.domain.vo.ProfileVisitVO;
import com.app.springapp.repository.ProfileVisitDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileVisitServiceImpl implements ProfileVisitService {

    private final ProfileVisitDAO profileVisitDAO;

    @Override
    public void recordVisit(Long ownerId, Long visitorId) {
        if (ownerId.equals(visitorId)) return;
        ProfileVisitVO vo = new ProfileVisitVO();
        vo.setOwnerMemberId(ownerId);
        vo.setVisitorMemberId(visitorId);
        profileVisitDAO.save(vo);
    }

    @Override
    public int getTodayVisitorCount(Long ownerId) {
        return profileVisitDAO.countTodayVisitors(ownerId);
    }
}
