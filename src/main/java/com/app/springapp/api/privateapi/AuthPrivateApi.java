package com.app.springapp.api.privateapi;

import com.app.springapp.domain.dto.JwtTokenDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 비공개 API", description = "로그아웃 (JWT 필요)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/private/auth")
public class AuthPrivateApi {

    private final AuthService authService;

    // 로그아웃: accessToken + refreshToken 블랙리스트 등록 후 쿠키 삭제
    @Operation(summary = "로그아웃", description = "토큰을 블랙리스트에 등록하고 쿠키를 삭제합니다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDTO> logout(
            @CookieValue(value = "accessToken",  required = false) String accessToken,
            @CookieValue(value = "refreshToken", required = false) String refreshToken,
            HttpServletResponse response) {

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        jwtTokenDTO.setAccessToken(accessToken);
        jwtTokenDTO.setRefreshToken(refreshToken);

        authService.logout(jwtTokenDTO);

        // 쿠키 만료 처리 (maxAge=0)
        ResponseCookie expiredAccess = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(0)
                .build();

        ResponseCookie expiredRefresh = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .secure(false)
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, expiredAccess.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, expiredRefresh.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "로그아웃 성공"));
    }
}
