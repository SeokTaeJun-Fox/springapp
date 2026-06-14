package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.ReplyReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyUpdateRequestDTO;
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
    public void getReplyTest() {
        ReplyReadRequestDTO replyReadRequestDTO = new ReplyReadRequestDTO();
        replyReadRequestDTO.setReplyId(2L);
        replyReadRequestDTO.setMemberId(1L);
        log.info("getReplies={}", replyService.getPostReply(replyReadRequestDTO));
    }

    @Test
    public void countPostTest() {
        log.info("{}", replyService.countReply(10L));
    }

    @Test
    public void writeReplyTest() {
        ReplyCreateRequestDTO replyCreateRequestDTO = new ReplyCreateRequestDTO();
        replyCreateRequestDTO.setPostId(20L);
        replyCreateRequestDTO.setMemberId(2L);
        replyCreateRequestDTO.setReplyContent("input text");
        replyService.writeReply(replyCreateRequestDTO);
    }

    @Test
    public void updateReplyTest() {
        ReplyUpdateRequestDTO replyUpdateRequestDTO = new ReplyUpdateRequestDTO();
        replyUpdateRequestDTO.setId(64L);
        replyUpdateRequestDTO.setReplyContent("수정된 댓글");
        replyService.updateReply(replyUpdateRequestDTO);
    }

    @Test
    public void deleteReplyTest() {
        replyService.deleteReply(57L);
    }

    @Test
    public void findAllByPostIdTest() {
        log.info("{}", replyService.findReplyIds(20L));
    }

    @Test
    public void deleteRepliesTest() {
        replyService.deleteReplies(28L);
    }
}
