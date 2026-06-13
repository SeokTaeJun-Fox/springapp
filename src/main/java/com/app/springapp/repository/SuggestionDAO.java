package com.app.springapp.repository;

import com.app.springapp.domain.vo.SuggestionVO;
import com.app.springapp.mapper.SuggestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SuggestionDAO {

    private final SuggestionMapper suggestionMapper;

    // 제안 저장
    public void save(SuggestionVO suggestionVO) {
        suggestionMapper.insert(suggestionVO);
    }

    // 프로젝트별 제안 목록 조회 (최신순)
    public List<SuggestionVO> findAllByProjectId(Long projectId) {
        return suggestionMapper.findAllByProjectId(projectId);
    }

    // 다른 프로젝트의 인기 제안 조회 (추천용)
    public List<SuggestionVO> findTopSuggestionsExcludingProject(Long projectId, int limit) {
        return suggestionMapper.findTopSuggestionsExcludingProject(projectId, limit);
    }
}