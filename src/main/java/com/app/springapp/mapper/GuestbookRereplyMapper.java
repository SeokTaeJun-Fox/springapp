package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookRereplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuestbookRereplyMapper {

    void insertRereply(GuestbookRereplyCreateRequestDTO guestbookRereplyCreateRequestDTO);

    List<GuestbookRereplyResponseDTO> selectRerepliesByGuestbookReplyId(Long guestbookReplyId);

    void updateGuestbookRereplyContentByWriterMemberId(GuestbookRereplyVO guestbookRereplyVO);

    void deleteRereply(GuestbookRereplyVO guestbookRereplyVO);

    void deleteLikeByRereplyId(Long rereplyId);
}
