package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;

public interface PostService {
    public PostResponseDTO FindPost(PostReadRequestDTO postReadRequestDTO);
}
