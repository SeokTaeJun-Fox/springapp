package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;

public interface ReplyLikeService {
    public void likeReply(ReplyLikeRequestDTO replyLikeRequestDTO);
    public ReplyLikeResponseDTO findReplyLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO);
}
