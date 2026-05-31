package com.app.springapp.service;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.vo.LogLikeVO;
import com.app.springapp.repository.LogLikeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogLikeServiceImpl implements LogLikeService {

    private final LogLikeDAO logLikeDAO;

    @Override
    public void toggleLike(Long logId, Long memberId) {
        if (logLikeDAO.isLiked(logId, memberId)) {
            LogLikeVO vo = new LogLikeVO();
            vo.setLogId(logId);
            vo.setMemberId(memberId);
            logLikeDAO.delete(vo);
        } else {
            LogLikeVO vo = new LogLikeVO();
            vo.setLogId(logId);
            vo.setMemberId(memberId);
            logLikeDAO.save(vo);
        }
    }

    @Override
    public List<LogListResponseDTO> getLikedLogs(Long memberId) {
        return logLikeDAO.findLikedLogsByMemberId(memberId);
    }
}
