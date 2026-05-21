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
    public void FindPostByMemberIdandIdTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setMemberId(2L);
        postReadRequestDTO.setPostId(20L);
        log.info("Post Read RequestDTO : {}", postService.FindPost(postReadRequestDTO));
    }
}
