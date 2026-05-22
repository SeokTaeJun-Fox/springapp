package com.app.springapp.api.publicapi;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 공개 API", description = "회원가입, 로그인, 토큰 재발급, 인증코드, 이메일 찾기, 비밀번호 재설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class AuthPublicApi {

    private final MemberService memberService;
    private final AuthService authService;

    // 회원가입
    @Operation(summary = "회원가입", description = "이메일/비밀번호로 로컬 회원가입을 합니다.")
    @PostMapping("/join")
    public ResponseEntity<ApiResponseDTO> join(@RequestBody MemberJoinRequestDTO dto) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(dto.getMemberEmail());
        memberDTO.setMemberPassword(dto.getMemberPassword());
        memberDTO.setMemberName(dto.getMemberName());
        memberDTO.setMemberPhone(dto.getMemberPhone());
        memberDTO.setMemberNickname(dto.getMemberNickname());
        memberDTO.setSocialMemberProvider("local");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.join(memberDTO));
    }

    // 로그인
    @Operation(summary = "로그인", description = "이메일/비밀번호로 로그인하고 JWT 쿠키를 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(
            @RequestBody MemberLoginRequestDTO dto,
            HttpServletResponse response) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(dto.getMemberEmail());
        memberDTO.setMemberPassword(dto.getMemberPassword());
        memberDTO.setSocialMemberProvider("local");

        JwtTokenDTO jwtTokenDTO = authService.login(memberDTO);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", jwtTokenDTO.getAccessToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", jwtTokenDTO.getRefreshToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60 * 24 * 7)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "로그인 성공"));
    }

    // Access Token 재발급
    @Operation(summary = "Access Token 재발급", description = "Refresh Token 쿠키로 새 Access Token을 발급합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<ApiResponseDTO> reissue(
            @CookieValue(value = "refreshToken", required = false) String refreshToken,
            HttpServletResponse response) {

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        jwtTokenDTO.setRefreshToken(refreshToken);

        JwtTokenDTO reissued = authService.reissueAccessToken(jwtTokenDTO);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", reissued.getAccessToken())
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "토큰 재발급 성공"));
    }

    // 이메일(아이디) 찾기
    @Operation(summary = "이메일 찾기", description = "이름과 전화번호로 가입된 이메일을 조회합니다.")
    @PostMapping("/email/find")
    public ResponseEntity<ApiResponseDTO> findEmail(@RequestBody MemberFindEmailRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.findMemberEmail(dto.getMemberName(), dto.getMemberPhone()));
    }

    // 휴대폰 인증 코드 발송
    @Operation(summary = "휴대폰 인증 코드 발송", description = "입력한 전화번호로 6자리 인증 코드를 발송합니다.")
    @PostMapping("/phone/send")
    public ResponseEntity<ApiResponseDTO> sendPhoneCode(@RequestBody PhoneVerificationSendRequestDTO dto) {
        boolean result = authService.sendMemberPhoneVerificationCode(dto.getMemberPhone());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(result, result ? "인증 코드 발송 완료" : "인증 코드 발송 실패"));
    }

    // 휴대폰 인증 코드 확인
    @Operation(summary = "휴대폰 인증 코드 확인", description = "발송된 인증 코드가 일치하는지 확인합니다.")
    @PostMapping("/phone/verify")
    public ResponseEntity<ApiResponseDTO> verifyPhoneCode(@RequestBody PhoneVerificationConfirmRequestDTO dto) {
        boolean result = authService.verifyMemberPhoneVerificationCode(dto.getMemberPhone(), dto.getCode());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(result, result ? "인증 성공" : "인증 실패"));
    }

    // 이메일 인증 코드 발송
    @Operation(summary = "이메일 인증 코드 발송", description = "입력한 이메일로 6자리 인증 코드를 발송합니다.")
    @PostMapping("/email/send")
    public ResponseEntity<ApiResponseDTO> sendEmailCode(@RequestBody EmailVerificationSendRequestDTO dto) {
        boolean result = authService.sendMemberEmailVerificationCode(dto.getMemberEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(result, result ? "인증 코드 발송 완료" : "인증 코드 발송 실패"));
    }

    // 이메일 인증 코드 확인
    @Operation(summary = "이메일 인증 코드 확인", description = "발송된 인증 코드가 일치하는지 확인합니다.")
    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponseDTO> verifyEmailCode(@RequestBody EmailVerificationConfirmRequestDTO dto) {
        boolean result = authService.verifyMemberEmailVerificationCode(dto.getMemberEmail(), dto.getCode());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(result, result ? "인증 성공" : "인증 실패"));
    }

    // 비밀번호 재설정
    @Operation(summary = "비밀번호 재설정", description = "이메일 인증 후 새 비밀번호로 재설정합니다.")
    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponseDTO> resetPassword(@RequestBody MemberFindPasswordRequestDTO dto) {
        boolean result = authService.resetPassword(dto.getMemberEmail(), dto.getNewPassword());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(result, result ? "비밀번호가 변경되었습니다." : "비밀번호 변경에 실패했습니다."));
    }
}
