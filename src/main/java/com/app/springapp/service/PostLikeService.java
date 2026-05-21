package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;

public interface PostLikeService {
    public void likePost(PostLikeRequestDTO postLikeRequestDTO);
    public PostLikeResponseDTO findPostLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO);
}
