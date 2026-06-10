package com.app.springapp.service;

import com.app.springapp.domain.dto.request.SuggestionCreateRequestDTO;
import com.app.springapp.domain.dto.response.SuggestionResponseDTO;

import java.util.List;

public interface SuggestionService {

    // 제안 작성
    void createSuggestion(SuggestionCreateRequestDTO requestDTO, Long memberId);

    // 프로젝트별 제안 목록 조회
    List<SuggestionResponseDTO> getSuggestionsByProjectId(Long projectId);

    // 프로젝트 생성 초기 - AI가 선별한 추천 제안 최대 3개 반환
    List<SuggestionResponseDTO> getRecommendedSuggestions(Long projectId);
}