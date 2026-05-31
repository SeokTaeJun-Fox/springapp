package com.app.springapp.service;

public interface ProfileVisitService {
    // 방문 기록 (자기 자신이면 무시)
    void recordVisit(Long ownerId, Long visitorId);
    // 오늘 방문자 수 조회
    int getTodayVisitorCount(Long ownerId);
}
