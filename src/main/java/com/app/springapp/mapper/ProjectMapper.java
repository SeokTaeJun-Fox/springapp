package com.app.springapp.mapper;

import com.app.springapp.domain.vo.ChecklistVO;
import com.app.springapp.domain.vo.ProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProjectMapper {

    // 프로젝트 추가
    void insertProject(ProjectVO projectVO);

    // 프로젝트 ID로 단건 조회
    ProjectVO findById(Long id);

    // 회원 ID로 프로젝트 목록 전체 조회
    List<ProjectVO> findAllByMemberId(Long memberId);

    // 프로젝트 ID로 단건 조회 (소유자 검증 없음 - 다른 사람 프로젝트 조회용)
    ProjectVO findByIdPublic(Long id);

    // 프로젝트 삭제
    void deleteById(Long id);

    // 프로젝트 수정
    void updateProject(ProjectVO projectVO);

    // 다른 사람들의 프로젝트 목록 조회 (내 프로젝트 제외)
    List<ProjectVO> findAllOtherProjects(Long memberId);

    // 프로젝트 ID로 체크리스트 목록 조회
    List<ChecklistVO> findChecklistsByProjectId(Long projectId);

    // 다른 사람의 프로젝트를 내 프로젝트로 복사
    void copyProject(ProjectVO projectVO);

    // 체크리스트 복사 (프로젝트 복사 시 함께 복사)
    void copyChecklist(ChecklistVO checklistVO);

    // 프로젝트 제목으로 검색 (내 프로젝트 제외)
    List<ProjectVO> searchOtherProjects(@Param("memberId") Long memberId, @Param("keyword") String keyword);

    // 같은 로그에 이미 존재하는 프로젝트 제목 목록 조회 (중복 방지용)
    List<String> findTitlesByLogIdAndMemberId(@Param("logId") Long logId, @Param("memberId") Long memberId);
}