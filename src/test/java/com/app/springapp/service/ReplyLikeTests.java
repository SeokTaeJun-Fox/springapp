package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
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
        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 55; j++) {
                if(Math.random() < 0.5) {
                    Long l1 = (long)i;
                    Long l2 = (long)j;
                    ReplyLikeRequestDTO replyLikeRequestDTO = new ReplyLikeRequestDTO();
                    replyLikeRequestDTO.setReplyId(l2);
                    replyLikeRequestDTO.setMemberId(l1);
                    replyLikeService.likeReply(replyLikeRequestDTO);
                }
            }
        }
    }

    @Test
    public void findPostLikeCountAndIsLikedTest() {
        ReplyLikeRequestDTO replyLikeRequestDTO = new ReplyLikeRequestDTO();
        replyLikeRequestDTO.setReplyId(55L);
        replyLikeRequestDTO.setMemberId(1L);
        log.info("{}",replyLikeService.findReplyLikeCountAndIsLiked(replyLikeRequestDTO));
    }

    @Test
    public void findPostLikeCountAndIsNotLikedTest() {

    }
}
