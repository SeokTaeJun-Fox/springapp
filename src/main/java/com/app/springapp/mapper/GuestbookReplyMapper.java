package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookReplyResponseDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuestbookReplyMapper {

    public void insertReply(GuestbookReplyCreateRequestDTO guestbookReplyCreateRequestDTO);
    public List<GuestbookReplyResponseDTO> selectRepliesByGuestbookId(Long guestbookId);
    public void updateGuestbookReplyContentByWriterMemberId(GuestbookReplyVO guestbookReplyVO);
    public void deleteReply(GuestbookReplyVO guestbookReplyVO);
}
