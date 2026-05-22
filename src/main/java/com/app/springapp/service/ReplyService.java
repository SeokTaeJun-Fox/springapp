package com.app.springapp.service;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostReadReplyResponseDTO;

import java.util.List;

public interface ReplyService {

    // 게시글id와 멤버id로 댓글 정보(댓글에 달린 대댓글포함) 목록 불러오기
    public List<PostReadReplyResponseDTO> getPostReplies(PostReadRequestDTO postReadRequestDTO);

    //작성자가 작성한 댓글 수
    public Integer countReply(Long memberId);
}
