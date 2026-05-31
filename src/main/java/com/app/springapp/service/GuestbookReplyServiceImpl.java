package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookReplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;
import com.app.springapp.exception.GuestbookException;
import com.app.springapp.repository.GuestbookReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestbookReplyServiceImpl implements GuestbookReplyService {

    private final GuestbookReplyDAO guestbookReplyDAO;

    @Override
    public void createReply(GuestbookReplyCreateRequestDTO guestbookReplyCreateRequestDTO) {
        guestbookReplyDAO.save(guestbookReplyCreateRequestDTO);
    }

    @Override
    public List<GuestbookReplyResponseDTO> findAllByGuestbookId(Long guestbookId) {
        return guestbookReplyDAO.findAllByGuestbookId(guestbookId)
                .orElseThrow(() -> new GuestbookException("해당 방명록이 없습니다."));
    }

    @Override
    public void updateReply(GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyDAO.updateReplyContent(guestbookReplyVO);
    }

    @Override
    public void deleteReply(GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyDAO.deleteReply(guestbookReplyVO);
    }
}
