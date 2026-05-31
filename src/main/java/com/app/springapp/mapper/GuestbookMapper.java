package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuestbookMapper {
    //  방명록 작성
    public void insert(GuestbookCreateRequestDTO guestbookCreateRequestDTO);
    //  방명록 목록 조회
    public List<GuestbookResponseDTO> selectListByOwnerMemberId(GuestbookResponseDTO guestbookResponseDTO);
    //  방명록 단건 조회
    public GuestbookResponseDTO selectById(Long id);
    //  방명록 글자로 검색
    public List<GuestbookResponseDTO> selectByText(String keyword);
    //  방명록 수정
    public void updateGuestbookContentByWriterMemberId(GuestbookVO guestbookVO);
    //  방명록 삭제
    public void deleteByGuestbookByWriterMemberIdAndOwnerMemberId(GuestbookVO guestbookVO);

}
