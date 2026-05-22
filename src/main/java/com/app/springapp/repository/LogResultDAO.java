package com.app.springapp.repository;

import com.app.springapp.domain.vo.LogResultVO;
import com.app.springapp.mapper.LogResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// 로그 분석 결과 관련 DB 접근을 담당하는 DAO 클래스
@Repository
@RequiredArgsConstructor
public class LogResultDAO {

    private final LogResultMapper logResultMapper;

    // 로그 ID로 분석 결과 조회
    public LogResultVO findByLogId(Long logId) {
        return logResultMapper.findByLogId(logId);
    }

    // 분석 결과 ID로 조회
    public LogResultVO findById(Long id) {
        return logResultMapper.findById(id);
    }
}