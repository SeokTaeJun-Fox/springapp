package com.app.springapp.mapper;

import com.app.springapp.domain.vo.LogResultVO;
import org.apache.ibatis.annotations.Mapper;

// 로그 분석 결과 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface LogResultMapper {

    // 로그 ID로 분석 결과 조회
    LogResultVO findByLogId(Long logId);

    // 분석 결과 추가
    void insert(LogResultVO logResultVO);

    // 분석 결과 ID로 조회
    LogResultVO findById(Long id);
}