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

// 체크리스트 서비스 구현체 - 체크리스트 추가, 조회, 수정, 삭제 비즈니스 로직 처리
@Slf4j
@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistDAO checklistDAO;

    /**
     * 체크리스트 추가
     * - 요청 DTO를 VO로 변환 후 DB에 저장
     * - 초기 완료/실패 상태는 "N"으로 설정
     *
     * @param requestDTO 추가할 체크리스트 정보 (ChecklistCreateRequestDTO)
     * @param memberId   현재 로그인한 회원 ID
     * @return 추가된 체크리스트 정보 (ChecklistResponseDTO)
     */
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

    /**
     * 프로젝트 ID로 체크리스트 목록 조회
     * - 해당 프로젝트의 전체 체크리스트를 DTO 리스트로 변환하여 반환
     *
     * @param projectId 조회할 프로젝트 ID
     * @return 체크리스트 목록 (List<ChecklistResponseDTO>)
     */
    @Override
    public List<ChecklistResponseDTO> getChecklists(Long projectId) {
        return checklistDAO.findAllByProjectId(projectId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * 체크리스트 수정
     * - 요청 DTO를 VO로 변환 후 DB 업데이트
     * - 완료/실패 상태값이 null인 경우 기본값 "N" 적용
     *
     * @param requestDTO 수정할 체크리스트 정보 (ChecklistUpdateRequestDTO)
     * @return 수정된 체크리스트 정보 (ChecklistResponseDTO)
     */
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

    // ChecklistVO → ChecklistResponseDTO 변환 헬퍼 메서드
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