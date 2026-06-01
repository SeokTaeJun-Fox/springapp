package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.LogAnalyzeRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.LogAnalyzeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
@Tag(name = "Log Analyze API", description = "LangChain 기반 페일로그 분석 API")
public class LogAnalyzeController {

    private final LogAnalyzeService logAnalyzeService;

    @Operation(summary = "로그 분석 및 저장", description = "작성한 로그를 LangChain으로 분석하고 PUBLISHED 상태로 최종 저장합니다.")
    @PostMapping("/analyze")
    public ResponseEntity<ApiResponseDTO> analyzeLog(@RequestBody LogAnalyzeRequestDTO request,
                                                     Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        Long memberId = memberDTO.getId();
        return ResponseEntity.ok(logAnalyzeService.analyzeLog(request, memberId));
    }

    @Operation(summary = "로그 분석 결과 조회", description = "로그 ID를 기반으로 AI 분석 결과를 포함한 전체 데이터를 조회합니다.")
    @GetMapping("/analyze/{logId}")
    public ResponseEntity<ApiResponseDTO> getLogAnalyzeResult(@org.springframework.web.bind.annotation.PathVariable Long logId,
                                                              jakarta.servlet.http.HttpServletRequest request,
                                                              jakarta.servlet.http.HttpServletResponse response,
                                                              Authentication authentication) {
        Long memberId = null;
        if (authentication != null && authentication.getPrincipal() instanceof MemberDTO) {
            memberId = ((MemberDTO) authentication.getPrincipal()).getId();
        }
        boolean shouldIncreaseReadCount = true;

        // 쿠키 검사 (동일 사용자의 중복 조회 방지)
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("viewed_log_" + logId)) {
                    shouldIncreaseReadCount = false;
                    break;
                }
            }
        }

        if (shouldIncreaseReadCount) {
            org.springframework.http.ResponseCookie newCookie = org.springframework.http.ResponseCookie.from("viewed_log_" + logId, "true")
                    .maxAge(60 * 60 * 24)
                    .path("/")
                    .sameSite("Lax")
                    .secure(false)
                    .httpOnly(true)
                    .build();
            response.addHeader(org.springframework.http.HttpHeaders.SET_COOKIE, newCookie.toString());
        }

        return ResponseEntity.ok(logAnalyzeService.getLogAnalyzeResult(logId, memberId, shouldIncreaseReadCount));
    }
}