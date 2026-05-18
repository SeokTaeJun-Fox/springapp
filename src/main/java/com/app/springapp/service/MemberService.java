package com.app.springapp.service;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.MemberPasswordUpdateRequestDTO;
import com.app.springapp.domain.dto.request.MemberUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.vo.MemberVO;

public interface MemberService {
    // 회원가입
    public ApiResponseDTO join(MemberDTO memberDTO);

    // 토큰 -> 회원 정보 조회
    public ApiResponseDTO me(Long id);

    // 회원 수정
    public ApiResponseDTO update(Long id, MemberUpdateRequestDTO memberUpdateRequestDTO);

    // 비밀번호 변경
    public ApiResponseDTO updatePassword(Long id, MemberPasswordUpdateRequestDTO memberPasswordUpdateRequestDTO);

    // 썸네일 수정
    public ApiResponseDTO updatePicture(MemberVO memberVO);

    // 회원 탈퇴
    public ApiResponseDTO withdraw(Long id);

}
