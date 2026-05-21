package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ChecklistCreateRequestDTO;
import com.app.springapp.domain.dto.request.ChecklistUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ChecklistResponseDTO;
import com.app.springapp.domain.vo.ChecklistVO;
import com.app.springapp.repository.ChecklistDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistDAO checklistDAO;

    // 체크리스트 추가
    @Override
    public ChecklistResponseDTO createChecklist(ChecklistCreateRequestDTO requestDTO, Long memberId) {
        ChecklistVO checklistVO = new ChecklistVO();
        checklistVO.setProjectId(requestDTO.getProjectId());
        checklistVO.setMemberId(memberId);
        checklistVO.setChecklistTitle(requestDTO.getChecklistTitle());
        checklistVO.setChecklistMemo(requestDTO.getChecklistMemo());
        checklistVO.setChecklistPriority(requestDTO.getChecklistPriority());
        checklistVO.setChecklistCompleted("N");
        checklistVO.setChecklistFailed("N");

        checklistDAO.insertChecklist(checklistVO);
        return toResponseDTO(checklistVO);
    }

    // 프로젝트별 체크리스트 목록 조회
    @Override
    public List<ChecklistResponseDTO> getChecklists(Long projectId) {
        return checklistDAO.findAllByProjectId(projectId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 체크리스트 수정
    @Override
    public ChecklistResponseDTO updateChecklist(ChecklistUpdateRequestDTO requestDTO) {
        ChecklistVO checklistVO = new ChecklistVO();
        checklistVO.setId(requestDTO.getId());
        checklistVO.setChecklistTitle(requestDTO.getChecklistTitle());
        checklistVO.setChecklistMemo(requestDTO.getChecklistMemo());
        checklistVO.setChecklistPriority(requestDTO.getChecklistPriority());
        checklistVO.setChecklistCompleted(requestDTO.getChecklistCompleted() != null
                ? requestDTO.getChecklistCompleted() : "N");
        checklistVO.setChecklistFailed(requestDTO.getChecklistFailed() != null
                ? requestDTO.getChecklistFailed() : "N");

        checklistDAO.updateChecklist(checklistVO);
        return toResponseDTO(checklistVO);
    }

    // 체크리스트 삭제
    @Override
    public void deleteChecklist(Long id) {
        checklistDAO.deleteById(id);
    }

    // VO → DTO 변환
    private ChecklistResponseDTO toResponseDTO(ChecklistVO vo) {
        ChecklistResponseDTO dto = new ChecklistResponseDTO();
        dto.setId(vo.getId());
        dto.setProjectId(vo.getProjectId());
        dto.setMemberId(vo.getMemberId());
        dto.setChecklistTitle(vo.getChecklistTitle());
        dto.setChecklistMemo(vo.getChecklistMemo());
        dto.setChecklistPriority(vo.getChecklistPriority());
        dto.setChecklistCompleted(vo.getChecklistCompleted());
        dto.setChecklistFailed(vo.getChecklistFailed());
        return dto;
    }
}