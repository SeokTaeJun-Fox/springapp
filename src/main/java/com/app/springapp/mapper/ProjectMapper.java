package com.app.springapp.mapper;

import com.app.springapp.domain.vo.ProjectVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 프로젝트 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface ProjectMapper {

    // 프로젝트 추가
    void insertProject(ProjectVO projectVO);

    // 프로젝트 ID로 단건 조회
    ProjectVO findById(Long id);

    // 회원 ID로 프로젝트 목록 전체 조회
    List<ProjectVO> findAllByMemberId(Long memberId);

    // 프로젝트 삭제
    void deleteById(Long id);

    // 프로젝트 수정
    void updateProject(ProjectVO projectVO);
}