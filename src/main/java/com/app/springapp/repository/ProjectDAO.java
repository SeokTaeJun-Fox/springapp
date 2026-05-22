package com.app.springapp.repository;

import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

// 프로젝트 관련 DB 접근을 담당하는 DAO 클래스
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
}