package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuestbookMapper {
    public void insert(GuestbookCreateRequestDTO guestbookCreateRequestDTO);
    public List<GuestbookResponseDTO> selectListByOwnerMemberId(GuestbookResponseDTO guestbookResponseDTO);
    public GuestbookResponseDTO selectById(Long id);
    public List<GuestbookResponseDTO> selectByText(String keyword);
    public void updateGuestbookContentByWriterMemberId(GuestbookVO guestbookVO);
    public void deleteByGuestbookByWriterMemberIdAndOwnerMemberId(GuestbookVO guestbookVO);

}
