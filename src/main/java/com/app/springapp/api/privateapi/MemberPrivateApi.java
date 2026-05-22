package com.app.springapp.api.privateapi;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.MemberPasswordUpdateRequestDTO;
import com.app.springapp.domain.dto.request.MemberUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 비공개 API", description = "회원 정보 조회/수정/비밀번호변경/탈퇴 (JWT 필요)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/private/member")
public class MemberPrivateApi {

    private final MemberService memberService;

    // 내 정보 조회
    @Operation(summary = "내 정보 조회", description = "JWT 토큰으로 로그인한 회원의 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<ApiResponseDTO> me(Authentication authentication) {
        MemberDTO memberDTO = getMemberFromAuth(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.me(memberDTO.getId()));
    }

    // 회원 정보 수정 (닉네임, 프로필 이미지, 전화번호)
    @Operation(summary = "회원 정보 수정", description = "닉네임, 프로필 이미지, 전화번호를 수정합니다.")
    @PutMapping
    public ResponseEntity<ApiResponseDTO> update(
            Authentication authentication,
            @RequestBody MemberUpdateRequestDTO dto) {
        MemberDTO memberDTO = getMemberFromAuth(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.update(memberDTO.getId(), dto));
    }

    // 비밀번호 변경
    @Operation(summary = "비밀번호 변경", description = "현재 비밀번호를 확인한 후 새 비밀번호로 변경합니다.")
    @PutMapping("/password")
    public ResponseEntity<ApiResponseDTO> updatePassword(
            Authentication authentication,
            @RequestBody MemberPasswordUpdateRequestDTO dto) {
        MemberDTO memberDTO = getMemberFromAuth(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.updatePassword(memberDTO.getId(), dto));
    }

    // 회원 탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ApiResponseDTO> withdraw(Authentication authentication) {
        MemberDTO memberDTO = getMemberFromAuth(authentication);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(memberService.withdraw(memberDTO.getId()));
    }

    // JWT 필터에서 SecurityContext에 저장된 MemberDTO 추출
    private MemberDTO getMemberFromAuth(Authentication authentication) {
        return (MemberDTO) authentication.getPrincipal();
    }
}
