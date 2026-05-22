package com.app.springapp.mapper;

import com.app.springapp.domain.vo.ChecklistVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 체크리스트 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface ChecklistMapper {

    // 체크리스트 추가
    void insertChecklist(ChecklistVO checklistVO);

    // 프로젝트 ID로 체크리스트 목록 조회
    List<ChecklistVO> findAllByProjectId(Long projectId);

    // 체크리스트 수정
    void updateChecklist(ChecklistVO checklistVO);

    // 체크리스트 삭제
    void deleteById(Long id);
}