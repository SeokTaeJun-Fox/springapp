package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;

import java.util.List;

public interface LogService {
    // 로그 목록 조회
    public ApiResponseDTO getLogList(int page, int size, String sort);

    // 키워드 검색
    public ApiResponseDTO getLogListByKeyword(String keyword, int page, int size, String sort);

    // 카테고리 필터
    public ApiResponseDTO getLogListByCategory(String category, int page, int size, String sort);

    ApiResponseDTO getMyLogList(Long memberId);
}