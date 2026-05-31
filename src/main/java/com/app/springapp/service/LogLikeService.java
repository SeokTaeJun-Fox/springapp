package com.app.springapp.service;

import com.app.springapp.domain.dto.response.LogListResponseDTO;

import java.util.List;

public interface LogLikeService {
    void toggleLike(Long logId, Long memberId);
    List<LogListResponseDTO> getLikedLogs(Long memberId);
}
