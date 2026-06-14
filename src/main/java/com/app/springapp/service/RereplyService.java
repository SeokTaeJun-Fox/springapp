package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyUpdateRequestDTO;
import com.app.springapp.domain.dto.response.RereplyResponseDTO;

import java.util.List;

public interface RereplyService {
    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> findAll(Long replyId);

    //대댓글id로 대댓글 목록 보여준다
    public RereplyResponseDTO find(Long reReplyId);

    //대댓글 작성
    public void writeRereply(RereplyCreateRequestDTO rereplyCreateRequestDTO);

    //대댓글 수정
    public void updateRereply(RereplyUpdateRequestDTO rereplyUpdateRequestDTO);

    //대댓글 삭제
    public void deleteRereply(Long rereplyId);

    // 대댓글 삭제(댓글 id를 입력값으로 해당 댓글에 달린 대댓글을 전부 삭제한다)
    public void deleteByRereplyId(Long replyId);
}
