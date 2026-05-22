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
        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 20; j++) {
                if(Math.random() < 0.5) {
                    Long l1 = (long)i;
                    Long l2 = (long)j;
                    PostLikeRequestDTO postLikeRequestDTO = new PostLikeRequestDTO();
                    postLikeRequestDTO.setPostId(l2);
                    postLikeRequestDTO.setMemberId(l1);
                    postLikeService.likePost(postLikeRequestDTO);
                }
            }
        }
    }

    @Test
    public void findPostLikeCountAndIsLikedTest() {
        PostLikeRequestDTO postLikeRequestDTO = new PostLikeRequestDTO();
        postLikeRequestDTO.setPostId(20L);
        postLikeRequestDTO.setMemberId(3L);
        log.info("{}",postLikeService.findPostLikeCountAndIsLiked(postLikeRequestDTO));
    }

    @Test
    public void findPostLikeCountAndIsNotLikedTest() {

    }
}
