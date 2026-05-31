package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;

import java.util.List;

public interface GuestbookService {

    // 방명록 작성
    public void createGuestbook(GuestbookCreateRequestDTO guestbookCreateRequestDTO);
    // 방명록 목록 조회
    public List<GuestbookResponseDTO> findAllByOwnerMemberId(GuestbookResponseDTO guestbookResponseDTO);
    // 방명록 단건 조회
    public GuestbookResponseDTO findById(Long id);
    // 방명록 텍스트 검색
    public List<GuestbookResponseDTO> findAllByText(String keyword);
    // 방명록 수정
    public void updateGuestbook(GuestbookVO guestbookVO);
    // 방명록 삭제
    public void deleteGuestbook(GuestbookVO guestbookVO);
}
