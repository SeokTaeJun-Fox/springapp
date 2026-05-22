package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.repository.PostDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostTests {

    @Autowired
    private PostService postService;

    @Test
    public void findPostByMemberIdandIdTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setMemberId(2L);
        postReadRequestDTO.setPostId(20L);
        log.info("Post Read RequestDTO : {}", postService.FindPost(postReadRequestDTO));
    }

    @Test
    public void findBeforePostTest() {
        log.info("{}", postService.findBeforePost(1L));
    }

    @Test
    public void findAfterPostTest() {
        log.info("{}", postService.findAfterPost(20L));
    }

    @Test
    public void countPostTest() {
        log.info("{}", postService.countPost(3L));
    }

    //2단계 테스트
    @Test
    public void findPostDetailTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setPostId(20L);
        postReadRequestDTO.setMemberId(2L);
        log.info("Post Read RequestDTO : {}", postService.getPostDetailInfo(postReadRequestDTO));
    }
}
