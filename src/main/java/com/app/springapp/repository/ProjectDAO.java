package com.app.springapp.repository;

import com.app.springapp.domain.vo.ChecklistVO;
import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectDAO {

    private final ProjectMapper projectMapper;

    // 프로젝트 추가
    public void insertProject(ProjectVO projectVO) {
        projectMapper.insertProject(projectVO);
    }

    // 프로젝트 ID로 단건 조회
    public ProjectVO findById(Long id) {
        return projectMapper.findById(id);
    }

    // 회원 ID로 프로젝트 목록 전체 조회
    public List<ProjectVO> findAllByMemberId(Long memberId) {
        return projectMapper.findAllByMemberId(memberId);
    }

    // 프로젝트 삭제
    public void deleteById(Long id) {
        projectMapper.deleteById(id);
    }

    // 프로젝트 수정
    public void updateProject(ProjectVO projectVO) {
        projectMapper.updateProject(projectVO);
    }

    // 다른 사람들의 프로젝트 목록 조회
    public List<ProjectVO> findAllOtherProjects(Long memberId) {
        return projectMapper.findAllOtherProjects(memberId);
    }

    // 프로젝트 ID로 단건 조회 (소유자 검증 없음 - 다른 사람 프로젝트 조회용)
    public ProjectVO findByIdPublic(Long id) {
        return projectMapper.findByIdPublic(id);
    }

    // 프로젝트 ID로 체크리스트 목록 조회
    public List<ChecklistVO> findChecklistsByProjectId(Long projectId) {
        return projectMapper.findChecklistsByProjectId(projectId);
    }

    // 다른 사람의 프로젝트를 내 프로젝트로 복사
    public void copyProject(ProjectVO projectVO) {
        projectMapper.copyProject(projectVO);
    }

    // 체크리스트 복사 (프로젝트 복사 시 함께 복사)
    public void copyChecklist(ChecklistVO checklistVO) {
        projectMapper.copyChecklist(checklistVO);
    }

    // 프로젝트 제목으로 검색 (내 프로젝트 제외)
    public List<ProjectVO> searchOtherProjects(Long memberId, String keyword) {
        return projectMapper.searchOtherProjects(memberId, keyword);
    }

    // 같은 로그에 이미 존재하는 프로젝트 제목 목록 조회 (중복 방지용)
    public List<String> findTitlesByLogIdAndMemberId(Long logId, Long memberId) {
        return projectMapper.findTitlesByLogIdAndMemberId(logId, memberId);
    }
}