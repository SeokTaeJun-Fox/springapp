package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookRereplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;

import java.util.List;

public interface GuestbookRereplyService {

    void createRereply(GuestbookRereplyCreateRequestDTO guestbookRereplyCreateRequestDTO);

    List<GuestbookRereplyResponseDTO> findAllByGuestbookReplyId(Long guestbookReplyId);

    void updateRereply(GuestbookRereplyVO guestbookRereplyVO);

    void deleteRereply(GuestbookRereplyVO guestbookRereplyVO);
}
