package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;

public interface ReplyLikeService {

    // 게시글 좋아요
    public void likeReply(ReplyLikeRequestDTO replyLikeRequestDTO);

    // 해당 게시글 좋아요 조회
    public ReplyLikeResponseDTO findReplyLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO);

}
