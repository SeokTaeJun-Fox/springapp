package com.app.springapp.service;

import com.app.springapp.domain.dto.request.SuggestionCreateRequestDTO;
import com.app.springapp.domain.dto.response.SuggestionResponseDTO;
import com.app.springapp.domain.vo.SuggestionVO;
import com.app.springapp.repository.SuggestionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionDAO suggestionDAO;

    // 제안 작성 - DTO를 VO로 변환 후 저장
    @Override
    public void createSuggestion(SuggestionCreateRequestDTO requestDTO, Long memberId) {
        SuggestionVO vo = new SuggestionVO();
        vo.setSuggestionTitle(requestDTO.getSuggestionTitle());
        vo.setChecklistId(requestDTO.getChecklistId());
        vo.setProjectId(requestDTO.getProjectId());
        vo.setMemberId(memberId);
        vo.setIsAddedList("N");
        suggestionDAO.save(vo);
    }

    // 프로젝트별 제안 목록 조회 - VO를 ResponseDTO로 변환 후 반환
    @Override
    public List<SuggestionResponseDTO> getSuggestionsByProjectId(Long projectId) {
        return suggestionDAO.findAllByProjectId(projectId).stream()
                .map(vo -> {
                    SuggestionResponseDTO dto = new SuggestionResponseDTO();
                    dto.setId(vo.getId());
                    dto.setSuggestionTitle(vo.getSuggestionTitle());
                    dto.setIsAddedList(vo.getIsAddedList());
                    dto.setChecklistId(vo.getChecklistId());
                    dto.setMemberId(vo.getMemberId());
                    dto.setProjectId(vo.getProjectId());
                    dto.setMemberNickname(vo.getMemberNickname());
                    dto.setMemberProfileImageUrl(vo.getMemberProfileImageUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}