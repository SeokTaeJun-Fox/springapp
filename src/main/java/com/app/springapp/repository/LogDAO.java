package com.app.springapp.repository;


import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.dto.response.LogResponseDTO;
import com.app.springapp.domain.vo.LogVO;
import com.app.springapp.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 로그 관련 DB 접근을 담당하는 DAO 클래스
@Repository
@RequiredArgsConstructor
public class LogDAO {

    private final LogMapper logMapper;

    // 전체 로그 목록 조회 (최신순/좋아요순/조회순 정렬, 페이징 적용)
    public List<LogListResponseDTO> findLogList(int offset, int size, String sort) {
        return logMapper.selectLogList(offset, size, sort);
    }

    // 키워드로 로그 목록 조회 (제목 기준 검색, 페이징 적용)
    public List<LogListResponseDTO> findLogListByKeyword(String keyword, int offset, int size, String sort) {
        return logMapper.selectLogListByKeyword(keyword, offset, size, sort);
    }

    // 카테고리로 로그 목록 조회 (페이징 적용)
    public List<LogListResponseDTO> findLogListByCategory(String category, int offset, int size, String sort) {
        return logMapper.selectLogListByCategory(category, offset, size, sort);
    }

    // 회원 ID로 내 로그 목록 전체 조회 (프로젝트 생성 모달용)
    public List<LogListResponseDTO> findAllByMemberId(Long memberId) {
        return logMapper.findAllByMemberId(memberId);
    }

    // 로그 작성
    public void save(LogVO logVO) {
        logMapper.insert(logVO);
    }

    // 로그 상세 조회
    public Optional<LogResponseDTO> findById(Long id) {
        return logMapper.selectById(id);
    }



    // 조회수 +1
    public void increaseReadCount(Long id) {
        logMapper.increaseReadCount(id);
    }
}