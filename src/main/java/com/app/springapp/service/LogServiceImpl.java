package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.repository.LogDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogDAO logDAO;

    // 전체 로그 목록 조회 (최신순/좋아요순/조회순 정렬, 페이징)
    @Override
    public ApiResponseDTO getLogList(int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogList(offset, size, sort);
        return new ApiResponseDTO(true, "로그 목록 조회 성공", list);
    }

    // 로그 키워드 검색 (제목 기준, 페이징)
    @Override
    public ApiResponseDTO getLogListByKeyword(String keyword, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByKeyword(keyword, offset, size, sort);
        return new ApiResponseDTO(true, "검색 결과 조회 성공", list);
    }

    // 로그 카테고리 필터 조회 (페이징)
    @Override
    public ApiResponseDTO getLogListByCategory(String category, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByCategory(category, offset, size, sort);
        return new ApiResponseDTO(true, "카테고리 조회 성공", list);
    }

    // 내 로그 목록 전체 조회 (프로젝트 생성 모달용 — 로그인한 사용자 기준)
    @Override
    public ApiResponseDTO getMyLogList(Long memberId) {
        List<LogListResponseDTO> list = logDAO.findAllByMemberId(memberId);
        return ApiResponseDTO.of(true, "내 로그 목록 조회 성공", list);
    }
}