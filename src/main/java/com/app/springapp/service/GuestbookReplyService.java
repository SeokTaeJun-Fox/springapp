package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookReplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;

import java.util.List;

public interface GuestbookReplyService {

    void createReply(GuestbookReplyCreateRequestDTO guestbookReplyCreateRequestDTO);

    List<GuestbookReplyResponseDTO> findAllByGuestbookId(Long guestbookId);

    void updateReply(GuestbookReplyVO guestbookReplyVO);

    void deleteReply(GuestbookReplyVO guestbookReplyVO);
}
