package com.app.springapp.repository;

import com.app.springapp.domain.vo.ChecklistVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChecklistDAO {
    // 체크리스트 추가
    void insertChecklist(ChecklistVO checklistVO);
    // 프로젝트별 체크리스트 목록 조회
    List<ChecklistVO> findAllByProjectId(Long projectId);
    // 체크리스트 수정
    void updateChecklist(ChecklistVO checklistVO);
    // 체크리스트 삭제
    void deleteById(Long id);
}