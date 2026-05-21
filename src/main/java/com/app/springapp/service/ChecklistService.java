package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ChecklistCreateRequestDTO;
import com.app.springapp.domain.dto.request.ChecklistUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ChecklistResponseDTO;

import java.util.List;

public interface ChecklistService {
    // 체크리스트 추가
    ChecklistResponseDTO createChecklist(ChecklistCreateRequestDTO requestDTO, Long memberId);
    // 프로젝트별 체크리스트 목록 조회
    List<ChecklistResponseDTO> getChecklists(Long projectId);
    // 체크리스트 수정
    ChecklistResponseDTO updateChecklist(ChecklistUpdateRequestDTO requestDTO);
    // 체크리스트 삭제
    void deleteChecklist(Long id);
}