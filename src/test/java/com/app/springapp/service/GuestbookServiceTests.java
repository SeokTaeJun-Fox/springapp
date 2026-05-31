package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService guestbookService;

    // 방명록 작성
    @Test
    public void createGuestbookTest() {
        GuestbookCreateRequestDTO dto = new GuestbookCreateRequestDTO();
        dto.setOwnerMemberId(1L);
        dto.setWriterMemberId(2L);
        dto.setGuestbookContent("테스트 방명록 내용입니다.");
        guestbookService.createGuestbook(dto);
        log.info("방명록 작성 완료");
    }

    // 방명록 목록 조회 (ownerMemberId 기준)
    @Test
    public void findAllByOwnerMemberIdTest() {
        GuestbookResponseDTO dto = new GuestbookResponseDTO();
        dto.setOwnerMemberId(1L);
        log.info("방명록 목록={}", guestbookService.findAllByOwnerMemberId(dto));
    }

    // 방명록 단건 조회
    @Test
    public void findByIdTest() {
        log.info("방명록 단건={}", guestbookService.findById(1L));
    }

    // 방명록 텍스트 검색
    @Test
    public void findAllByTextTest() {
        log.info("방명록 검색 결과={}", guestbookService.findAllByText("테스트"));
    }

    // 방명록 수정
    @Test
    public void updateGuestbookTest() {
        GuestbookVO vo = new GuestbookVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        vo.setGuestbookContent("수정된 방명록 내용입니다.");
        guestbookService.updateGuestbook(vo);
        log.info("방명록 수정 완료");
    }

    // 방명록 삭제
    @Test
    public void deleteGuestbookTest() {
        GuestbookVO vo = new GuestbookVO();
        vo.setId(1L);
        vo.setWriterMemberId(2L);
        vo.setOwnerMemberId(1L);
        guestbookService.deleteGuestbook(vo);
        log.info("방명록 삭제 완료");
    }
}
