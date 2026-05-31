package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookRereplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;
import com.app.springapp.exception.GuestbookException;
import com.app.springapp.repository.GuestbookRereplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestbookRereplyServiceImpl implements GuestbookRereplyService {

    private final GuestbookRereplyDAO guestbookRereplyDAO;

    @Override
    public void createRereply(GuestbookRereplyCreateRequestDTO guestbookRereplyCreateRequestDTO) {
        guestbookRereplyDAO.save(guestbookRereplyCreateRequestDTO);
    }

    @Override
    public List<GuestbookRereplyResponseDTO> findAllByGuestbookReplyId(Long guestbookReplyId) {
        return guestbookRereplyDAO.findAllByGuestbookReplyId(guestbookReplyId)
                .orElseThrow(() -> new GuestbookException("해당 방명록 댓글이 없습니다."));
    }

    @Override
    public void updateRereply(GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyDAO.updateRereplyContent(guestbookRereplyVO);
    }

    @Override
    public void deleteRereply(GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyDAO.deleteRereply(guestbookRereplyVO);
    }
}
