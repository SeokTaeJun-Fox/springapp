package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GuestbookRereplyServiceTests {

    @Autowired
    private GuestbookRereplyService guestbookRereplyService;

    // 대댓글 작성
    @Test
    public void createRereplyTest() {
        GuestbookRereplyCreateRequestDTO dto = new GuestbookRereplyCreateRequestDTO();
        dto.setGuestbookReplyId(1L);
        dto.setWriterMemberId(2L);
        dto.setGuestbookRereplyContent("테스트 대댓글 내용입니다.");
        guestbookRereplyService.createRereply(dto);
        log.info("대댓글 작성 완료");
    }

    // 대댓글 목록 조회 (guestbookReplyId 기준)
    @Test
    public void findAllByGuestbookReplyIdTest() {
        log.info("대댓글 목록={}", guestbookRereplyService.findAllByGuestbookReplyId(1L));
    }

    // 대댓글 수정
    @Test
    public void updateRereplyTest() {
        GuestbookRereplyVO vo = new GuestbookRereplyVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        vo.setGuestbookRereplyContent("수정된 대댓글 내용입니다.");
        guestbookRereplyService.updateRereply(vo);
        log.info("대댓글 수정 완료");
    }

    // 대댓글 삭제
    @Test
    public void deleteRereplyTest() {
        GuestbookRereplyVO vo = new GuestbookRereplyVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        guestbookRereplyService.deleteRereply(vo);
        log.info("대댓글 삭제 완료");
    }
}
