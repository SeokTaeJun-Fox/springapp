package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.MemberPasswordUpdateRequestDTO;
import com.app.springapp.domain.dto.request.MemberUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 API", description = "회원 정보 조회/수정/탈퇴 (JWT 필요)")
@RestController
@RequestMapping("/private/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "내 정보 조회", description = "JWT 토큰으로 로그인한 회원의 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<ApiResponseDTO> me(@AuthenticationPrincipal MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.me(memberDTO.getId()));
    }

    @Operation(summary = "회원 정보 수정", description = "닉네임, 프로필 이미지, 전화번호를 수정합니다.")
    @PutMapping
    public ResponseEntity<ApiResponseDTO> update(
            @AuthenticationPrincipal MemberDTO memberDTO,
            @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO) {
        return ResponseEntity.ok(memberService.update(memberDTO.getId(), memberUpdateRequestDTO));
    }

    @Operation(summary = "비밀번호 변경", description = "현재 비밀번호를 확인한 후 새 비밀번호로 변경합니다.")
    @PutMapping("/password")
    public ResponseEntity<ApiResponseDTO> updatePassword(
            @AuthenticationPrincipal MemberDTO memberDTO,
            @RequestBody MemberPasswordUpdateRequestDTO memberPasswordUpdateRequestDTO) {
        return ResponseEntity.ok(memberService.updatePassword(memberDTO.getId(), memberPasswordUpdateRequestDTO));
    }

    @Operation(summary = "회원 탈퇴", description = "회원을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ApiResponseDTO> withdraw(@AuthenticationPrincipal MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.withdraw(memberDTO.getId()));
    }
}
