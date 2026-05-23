package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;

public interface PostLikeService {
    public PostLikeResponseDTO likePost(PostLikeRequestDTO postLikeRequestDTO);
    public PostLikeResponseDTO findPostLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO);

    // 게시글 좋아요 취소
    public PostLikeResponseDTO cancelPostLike(PostLikeRequestDTO postLikeRequestDTO);
}
