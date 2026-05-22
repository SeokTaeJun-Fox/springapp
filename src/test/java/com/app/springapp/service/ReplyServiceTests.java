package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void getRepliesTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setPostId(20L);
        postReadRequestDTO.setMemberId(1L);
        log.info("getReplies={}", replyService.getPostReplies(postReadRequestDTO));
    }

    @Test
    public void countPostTest() {
        log.info("{}", replyService.countReply(10L));
    }
}
