package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GuestbookReplyServiceTests {

    @Autowired
    private GuestbookReplyService guestbookReplyService;

    // 답글 작성
    @Test
    public void createReplyTest() {
        GuestbookReplyCreateRequestDTO dto = new GuestbookReplyCreateRequestDTO();
        dto.setGuestbookId(1L);
        dto.setWriterMemberId(2L);
        dto.setGuestbookReplyContent("테스트 답글 내용입니다.");
        guestbookReplyService.createReply(dto);
        log.info("답글 작성 완료");
    }

    // 답글 목록 조회 (guestbookId 기준)
    @Test
    public void findAllByGuestbookIdTest() {
        log.info("답글 목록={}", guestbookReplyService.findAllByGuestbookId(1L));
    }

    // 답글 수정
    @Test
    public void updateReplyTest() {
        GuestbookReplyVO vo = new GuestbookReplyVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        vo.setGuestbookReplyContent("수정된 답글 내용입니다.");
        guestbookReplyService.updateReply(vo);
        log.info("답글 수정 완료");
    }

    // 답글 삭제
    @Test
    public void deleteReplyTest() {
        GuestbookReplyVO vo = new GuestbookReplyVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        guestbookReplyService.deleteReply(vo);
        log.info("답글 삭제 완료");
    }
}
