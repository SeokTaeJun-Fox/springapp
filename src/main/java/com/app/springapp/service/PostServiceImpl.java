package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.exception.PostException;
import com.app.springapp.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    @Override
    public PostResponseDTO FindPost(PostReadRequestDTO postReadRequestDTO) {
        return postDAO.findById(postReadRequestDTO).orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }
}
