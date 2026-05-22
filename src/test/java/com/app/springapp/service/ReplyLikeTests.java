package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ReplyLikeTests {

    @Autowired
    private ReplyLikeService replyLikeService;

    @Test
    public void ReplyLikeTest() {
        for(int i = 0; i < 1300; i++) {
            Long replyId = 1+(long)(Math.random()*55);
            Long memberId = 1+(long)(Math.random()*10);
            ReplyLikeRequestDTO replyLikeRequestDTO = new ReplyLikeRequestDTO();
            replyLikeRequestDTO.setReplyId(replyId);
            replyLikeRequestDTO.setMemberId(memberId);
            replyLikeService.likeReply(replyLikeRequestDTO);
        }
    }

    @Test
    public void findPostLikeCountAndIsLikedTest() {
        ReplyLikeRequestDTO replyLikeRequestDTO = new ReplyLikeRequestDTO();
        replyLikeRequestDTO.setReplyId(55L);
        replyLikeRequestDTO.setMemberId(2L);
        log.info("{}",replyLikeService.findReplyLikeCountAndIsLiked(replyLikeRequestDTO));
    }

    @Test
    public void findPostLikeCountAndIsNotLikedTest() {

    }
}
