package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogCreateRequestDTO;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;

import java.util.List;

// 로그 서비스 인터페이스
public interface LogService {

    // 전체 로그 목록 조회 (정렬 기준, 페이징 적용)
    public ApiResponseDTO getLogList(int page, int size, String sort);

    // 키워드로 로그 목록 조회 (제목 기준 검색, 페이징 적용)
    public ApiResponseDTO getLogListByKeyword(String keyword, int page, int size, String sort);

    // 카테고리로 로그 목록 조회 (페이징 적용)
    public ApiResponseDTO getLogListByCategory(String category, int page, int size, String sort);

    // 회원 ID로 내 로그 목록 전체 조회 (프로젝트 생성 모달용)
    ApiResponseDTO getMyLogList(Long memberId);

    // 로그 작성
    ApiResponseDTO createLog(LogCreateRequestDTO dto, Long memberId);

    // 로그 상세 조회
    ApiResponseDTO getLog(Long id);

    // 인기 솔루션 조회 (좋아요 10개 이상, 로그 목록 페이지 노출용)
    ApiResponseDTO getPopularSolutions();


}