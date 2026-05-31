package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookReplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;
import com.app.springapp.mapper.GuestbookReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GuestbookReplyDAO {

    private final GuestbookReplyMapper guestbookReplyMapper;

    public void save(GuestbookReplyCreateRequestDTO guestbookReplyCreateRequestDTO) {
        guestbookReplyMapper.insertReply(guestbookReplyCreateRequestDTO);
    }

    public Optional<List<GuestbookReplyResponseDTO>> findAllByGuestbookId(Long guestbookId) {
        return Optional.ofNullable(guestbookReplyMapper.selectRepliesByGuestbookId(guestbookId));
    }

    public void updateReplyContent(GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyMapper.updateGuestbookReplyContentByWriterMemberId(guestbookReplyVO);
    }

    public void deleteReply(GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyMapper.deleteReply(guestbookReplyVO);
    }
}
