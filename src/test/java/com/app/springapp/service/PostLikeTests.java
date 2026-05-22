package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PostLikeTests {

    @Autowired
    private PostLikeService postLikeService;

    @Test
    public void likePostTest() {
//        for(int i = 0; i < 500; i++) {
//            Long postId = 1+(long)(Math.random()*20);
//            Long memberId = 1+(long)(Math.random()*10);
//            PostLikeRequestDTO postLikeRequestDTO = new PostLikeRequestDTO();
//            postLikeRequestDTO.setPostId(postId);
//            postLikeRequestDTO.setMemberId(memberId);
//            postLikeService.likePost(postLikeRequestDTO);
//        }
        PostLikeRequestDTO postLikeRequestDTO = new PostLikeRequestDTO();
        postLikeRequestDTO.setPostId(1L);
        postLikeRequestDTO.setMemberId(1L);
        postLikeService.likePost(postLikeRequestDTO);
    }

    @Test
    public void findPostLikeCountAndIsLikedTest() {
        PostLikeRequestDTO postLikeRequestDTO = new PostLikeRequestDTO();
        postLikeRequestDTO.setPostId(19L);
        postLikeRequestDTO.setMemberId(1L);
        log.info("{}",postLikeService.findPostLikeCountAndIsLiked(postLikeRequestDTO));
    }

    @Test
    public void findPostLikeCountAndIsNotLikedTest() {

    }
}
