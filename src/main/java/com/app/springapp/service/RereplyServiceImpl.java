package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyUpdateRequestDTO;
import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import com.app.springapp.repository.ReplyDAO;
import com.app.springapp.repository.RereplyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RereplyServiceImpl implements RereplyService {

    private final RereplyDAO rereplyDAO;
    private final ReplyDAO replyDAO;

    //댓글id로 대댓글 목록 보여준다
    @Override
    public List<RereplyResponseDTO> findAll(Long replyId) {
        return rereplyDAO.findAll(replyId);
    }

    //대댓글id로 대댓글 목록 보여준다
    @Override
    public RereplyResponseDTO find(Long reReplyId) {
        return rereplyDAO.find(reReplyId);
    }

    // 대댓글 작성
    @Override
    public void writeRereply(RereplyCreateRequestDTO rereplyCreateRequestDTO) {
        rereplyDAO.save(rereplyCreateRequestDTO);
    }

    @Override
    public void updateRereply(RereplyUpdateRequestDTO rereplyUpdateRequestDTO) {
        rereplyDAO.update(rereplyUpdateRequestDTO);
    }


    // 대댓글 삭제
    @Override
    public void deleteRereply(Long rereplyId) {
        rereplyDAO.delete(rereplyId);
    }

    // 대댓글 삭제(댓글 id를 입력값으로 해당 댓글에 달린 대댓글을 전부 삭제한다)
    @Override
    public void deleteByRereplyId(Long replyId) {
        rereplyDAO.deleteByRereplyId(replyId);
    }
}
