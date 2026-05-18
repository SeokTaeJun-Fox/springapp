package com.app.springapp.controller;

import com.app.springapp.domain.dto.JwtTokenDTO;
import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.*;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.AuthService;
import com.app.springapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 API", description = "회원가입, 로그인, 토큰 재발급, 인증 코드")
@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "이메일/비밀번호로 로컬 회원가입을 합니다.")
    @PostMapping("/join")
    public ResponseEntity<ApiResponseDTO> join(@RequestBody MemberJoinRequestDTO memberJoinRequestDTO) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(memberJoinRequestDTO.getMemberEmail());
        memberDTO.setMemberPassword(memberJoinRequestDTO.getMemberPassword());
        memberDTO.setMemberName(memberJoinRequestDTO.getMemberName());
        memberDTO.setMemberPhone(memberJoinRequestDTO.getMemberPhone());
        memberDTO.setMemberNickname(memberJoinRequestDTO.getMemberNickname());
        memberDTO.setSocialMemberProvider("local");
        return ResponseEntity.ok(memberService.join(memberDTO));
    }

    @Operation(summary = "로그인", description = "이메일/비밀번호로 로그인하고 JWT 쿠키를 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(
            @RequestBody MemberLoginRequestDTO memberLoginRequestDTO,
            HttpServletResponse response) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(memberLoginRequestDTO.getMemberEmail());
        memberDTO.setMemberPassword(memberLoginRequestDTO.getMemberPassword());
        memberDTO.setSocialMemberProvider("local");

        JwtTokenDTO jwtTokenDTO = authService.login(memberDTO);

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", jwtTokenDTO.getAccessToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60)
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", jwtTokenDTO.getRefreshToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60 * 24 * 7)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return ResponseEntity.ok(ApiResponseDTO.of(true, "로그인 성공"));
    }

    @Operation(summary = "Access Token 재발급", description = "Refresh Token으로 새 Access Token을 발급합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<ApiResponseDTO> reissue(
            @CookieValue(value = "refreshToken", required = false) String refreshToken,
            HttpServletResponse response) {

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        jwtTokenDTO.setRefreshToken(refreshToken);

        JwtTokenDTO reissuedToken = authService.reissueAccessToken(jwtTokenDTO);

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", reissuedToken.getAccessToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());

        return ResponseEntity.ok(ApiResponseDTO.of(true, "토큰 재발급 성공"));
    }

    @Operation(summary = "휴대폰 인증 코드 발송", description = "입력한 전화번호로 6자리 인증 코드를 발송합니다.")
    @PostMapping("/phone/send")
    public ResponseEntity<ApiResponseDTO> sendPhoneVerification(
            @RequestBody PhoneVerificationSendRequestDTO dto) {
        boolean result = authService.sendMemberPhoneVerificationCode(dto.getMemberPhone());
        return ResponseEntity.ok(ApiResponseDTO.of(result, result ? "인증 코드 발송 완료" : "인증 코드 발송 실패"));
    }

    @Operation(summary = "휴대폰 인증 코드 확인", description = "발송된 인증 코드가 일치하는지 확인합니다.")
    @PostMapping("/phone/verify")
    public ResponseEntity<ApiResponseDTO> verifyPhoneCode(
            @RequestBody PhoneVerificationConfirmRequestDTO dto) {
        boolean result = authService.verifyMemberPhoneVerificationCode(dto.getMemberPhone(), dto.getCode());
        return ResponseEntity.ok(ApiResponseDTO.of(result, result ? "인증 성공" : "인증 실패"));
    }

    @Operation(summary = "이메일 인증 코드 발송", description = "입력한 이메일로 6자리 인증 코드를 발송합니다.")
    @PostMapping("/email/send")
    public ResponseEntity<ApiResponseDTO> sendEmailVerification(
            @RequestBody EmailVerificationSendRequestDTO dto) {
        boolean result = authService.sendMemberEmailVerificationCode(dto.getMemberEmail());
        return ResponseEntity.ok(ApiResponseDTO.of(result, result ? "인증 코드 발송 완료" : "인증 코드 발송 실패"));
    }

    @Operation(summary = "이메일 인증 코드 확인", description = "발송된 인증 코드가 일치하는지 확인합니다.")
    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponseDTO> verifyEmailCode(
            @RequestBody EmailVerificationConfirmRequestDTO dto) {
        boolean result = authService.verifyMemberEmailVerificationCode(dto.getMemberEmail(), dto.getCode());
        return ResponseEntity.ok(ApiResponseDTO.of(result, result ? "인증 성공" : "인증 실패"));
    }

    @Operation(summary = "비밀번호 재설정", description = "이메일 인증 후 새 비밀번호로 재설정합니다.")
    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponseDTO> resetPassword(@RequestBody MemberFindPasswordRequestDTO dto) {
        boolean result = authService.resetPassword(dto.getMemberEmail(), dto.getNewPassword());
        return ResponseEntity.ok(ApiResponseDTO.of(result, result ? "비밀번호가 변경되었습니다." : "비밀번호 변경에 실패했습니다."));
    }
}
