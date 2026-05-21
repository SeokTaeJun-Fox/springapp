package com.app.springapp.repository;

import com.app.springapp.domain.vo.ProjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDAO {
    void insertProject(ProjectVO projectVO);
    ProjectVO findById(Long id);
    List<ProjectVO> findAllByMemberId(Long memberId);
    void deleteById(Long id);
}