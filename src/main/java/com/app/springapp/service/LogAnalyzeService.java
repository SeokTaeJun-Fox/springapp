package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogAnalyzeRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;

public interface LogAnalyzeService {
    ApiResponseDTO analyzeLog(LogAnalyzeRequestDTO request, Long memberId);
    ApiResponseDTO getLogAnalyzeResult(Long logId, Long memberId, boolean shouldIncreaseReadCount);
}
