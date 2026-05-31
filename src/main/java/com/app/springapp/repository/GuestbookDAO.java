package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import com.app.springapp.mapper.GuestbookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class GuestbookDAO {

    private final GuestbookMapper guestbookMapper;

    // 방명록 작성
    public void save(GuestbookCreateRequestDTO guestbookCreateRequestDTO) {
        guestbookMapper.insert(guestbookCreateRequestDTO);
    }

    // 방명록 목록 조회
    public Optional<List<GuestbookResponseDTO>> findAllByOwnerMemberId(GuestbookResponseDTO guestbookResponseDTO) {
        return Optional.ofNullable(guestbookMapper.selectListByOwnerMemberId(guestbookResponseDTO));
    }

    // 방명록 단건 조회
    public Optional<GuestbookResponseDTO> findById(Long id) {
        return Optional.ofNullable(guestbookMapper.selectById(id));
    }

    // 방명록 텍스트 검색
    public Optional<List<GuestbookResponseDTO>> findAllByText(String keyword) {
        return Optional.ofNullable(guestbookMapper.selectByText(keyword));
    }

    // 방명록 수정
    public void updateGuestbookContentByWriterMemberId(GuestbookVO guestbookVO) {
        guestbookMapper.updateGuestbookContentByWriterMemberId(guestbookVO);
    }

    // 방명록 삭제
    public void deleteGuestbookByWriterMemberIdAndOwnerMemberId(GuestbookVO guestbookVO) {
        guestbookMapper.deleteByGuestbookByWriterMemberIdAndOwnerMemberId(guestbookVO);
    }
}