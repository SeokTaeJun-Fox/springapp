package com.app.springapp.repository;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogDAO {
    private final LogMapper logMapper;

    // 로그 목록 조회 (최신순/좋아요순/조회순 정렬, 페이징)
    public List<LogListResponseDTO> findLogList(int offset, int size, String sort) {
        return logMapper.selectLogList(offset, size, sort);
    }

    // 로그 키워드 검색 (제목 기준, 페이징)
    public List<LogListResponseDTO> findLogListByKeyword(String keyword, int offset, int size, String sort) {
        return logMapper.selectLogListByKeyword(keyword, offset, size, sort);
    }

    // 로그 카테고리 필터 조회 (페이징)
    public List<LogListResponseDTO> findLogListByCategory(String category, int offset, int size, String sort) {
        return logMapper.selectLogListByCategory(category, offset, size, sort);
    }

    // 내 로그 목록 전체 조회 (프로젝트 생성 모달용 — 로그인한 사용자 기준)
    public List<LogListResponseDTO> findAllByMemberId(Long memberId) {
        return logMapper.findAllByMemberId(memberId);
    }
}