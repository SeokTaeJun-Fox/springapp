package com.app.springapp.repository;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.vo.LogLikeVO;
import com.app.springapp.mapper.LogLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogLikeDAO {

    private final LogLikeMapper logLikeMapper;

    public void save(LogLikeVO logLikeVO) {
        logLikeMapper.insert(logLikeVO);
    }

    public void delete(LogLikeVO logLikeVO) {
        logLikeMapper.delete(logLikeVO);
    }

    public boolean isLiked(Long logId, Long memberId) {
        LogLikeVO vo = new LogLikeVO();
        vo.setLogId(logId);
        vo.setMemberId(memberId);
        return logLikeMapper.isLiked(vo) > 0;
    }

    public List<LogListResponseDTO> findLikedLogsByMemberId(Long memberId) {
        return logLikeMapper.selectLikedLogsByMemberId(memberId);
    }
}
