package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;
import com.app.springapp.exception.PostLikeException;
import com.app.springapp.repository.PostLikeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeDAO postLikeDAO;

    //게시글 좋아요 기능
    @Override
    public void likePost(PostLikeRequestDTO postLikeRequestDTO) {
        postLikeDAO.save(postLikeRequestDTO);
    }

    //게시글 좋아요 갯수, 해당 멤버가 좋아요를 클릭했는지 확인하는 기능
    @Override
    public PostLikeResponseDTO findPostLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO) {
        return postLikeDAO.findPostLikeCountAndIsLiked(postLikeRequestDTO).orElseThrow(() -> new PostLikeException("게시글 좋아요, 좋아요 여부를 불러오지 못했습니다.", HttpStatus.NOT_FOUND));
    }
}
