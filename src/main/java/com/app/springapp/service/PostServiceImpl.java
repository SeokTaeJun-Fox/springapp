package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public PostResponseDTO FindPost(PostReadRequestDTO postReadRequestDTO) {
        return postDAO.findByMemberIdAndId(postReadRequestDTO);
    }
}
