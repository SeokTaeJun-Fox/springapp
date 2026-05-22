package com.app.springapp.repository;

import com.app.springapp.domain.vo.ChecklistVO;
import com.app.springapp.mapper.ChecklistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

// 체크리스트 관련 DB 접근을 담당하는 DAO 클래스
@Repository
@RequiredArgsConstructor
public class ChecklistDAO {

    private final ChecklistMapper checklistMapper;

    // 체크리스트 추가
    public void insertChecklist(ChecklistVO checklistVO) {
        checklistMapper.insertChecklist(checklistVO);
    }

    // 프로젝트 ID로 체크리스트 목록 조회
    public List<ChecklistVO> findAllByProjectId(Long projectId) {
        return checklistMapper.findAllByProjectId(projectId);
    }

    // 체크리스트 수정
    public void updateChecklist(ChecklistVO checklistVO) {
        checklistMapper.updateChecklist(checklistVO);
    }

    // 체크리스트 삭제
    public void deleteById(Long id) {
        checklistMapper.deleteById(id);
    }
}