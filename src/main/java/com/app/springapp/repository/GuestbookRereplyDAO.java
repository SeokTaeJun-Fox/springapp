package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookRereplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;
import com.app.springapp.mapper.GuestbookRereplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GuestbookRereplyDAO {

    private final GuestbookRereplyMapper guestbookRereplyMapper;

    public void save(GuestbookRereplyCreateRequestDTO guestbookRereplyCreateRequestDTO) {
        guestbookRereplyMapper.insertRereply(guestbookRereplyCreateRequestDTO);
    }

    public Optional<List<GuestbookRereplyResponseDTO>> findAllByGuestbookReplyId(Long guestbookReplyId) {
        return Optional.ofNullable(guestbookRereplyMapper.selectRerepliesByGuestbookReplyId(guestbookReplyId));
    }

    public void updateRereplyContent(GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyMapper.updateGuestbookRereplyContentByWriterMemberId(guestbookRereplyVO);
    }

    public void deleteRereply(GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyMapper.deleteRereply(guestbookRereplyVO);
    }

    public void deleteLikeByRereplyId(Long rereplyId) {
        guestbookRereplyMapper.deleteLikeByRereplyId(rereplyId);
    }
}
