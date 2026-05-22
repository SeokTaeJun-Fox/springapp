package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ProjectCreateRequestDTO;
import com.app.springapp.domain.dto.response.ProjectResponseDTO;
import com.app.springapp.domain.vo.ProjectVO;

import java.util.List;

public interface ProjectService {

    // AI가 로그 분석 결과를 기반으로 프로젝트 자동 생성
    ProjectResponseDTO createProjectByAI(ProjectCreateRequestDTO requestDTO, Long memberId);

    // 회원 ID로 내 프로젝트 목록 전체 조회
    List<ProjectResponseDTO> getMyProjects(Long memberId);

    // 프로젝트 ID로 단건 조회 (본인 소유 여부 검증 포함)
    ProjectResponseDTO getProject(Long projectId, Long memberId);

    // 프로젝트 ID로 단건 조회 (소유자 검증 없음 - 다른 사람 프로젝트 조회용)
    ProjectResponseDTO getProjectPublic(Long projectId);

    // 프로젝트 삭제 (본인 소유 여부 검증 포함)
    void deleteProject(Long projectId, Long memberId);

    // 프로젝트 수정 (본인 소유 여부 검증 포함)
    void updateProject(Long projectId, Long memberId, ProjectVO projectVO);

    // 다른 사람들의 프로젝트 목록 조회 (내 프로젝트 제외, 체크리스트 포함)
    List<ProjectResponseDTO> getOtherProjects(Long memberId);

    // 다른 사람의 프로젝트를 내 프로젝트로 복사
    void copyProject(Long projectId, Long memberId);

    // 프로젝트 제목으로 검색 (내 프로젝트 제외)
    List<ProjectResponseDTO> searchOtherProjects(Long memberId, String keyword);
}