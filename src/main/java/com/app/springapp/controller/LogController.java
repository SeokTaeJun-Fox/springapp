package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.LogCreateRequestDTO;
import com.app.springapp.domain.dto.request.LogUpdateRequestDTO;
import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// 로그 관련 요청을 처리하는 컨트롤러 (목록 조회, 검색, 카테고리 필터, 내 로그 조회) - 기본 URL: /api/logs
@Tag(name = "로그 API", description = "페일로그 목록 조회, 작성, 삭제, 좋아요, 인기 솔루션")
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    // 전체 로그 목록 조회 - GET /api/logs (최신순/좋아요순/조회순 정렬, 페이징 지원)
    @Operation(summary = "로그 목록 조회", description = "최신순으로 로그 목록을 페이징하여 반환합니다.")
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getLogList(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "9")  int size,
            @RequestParam(defaultValue = "최신순") String sort) {
        return ResponseEntity.ok(logService.getLogList(page, size, sort));
    }

    // 로그 키워드 검색 - GET /api/logs/search (제목 기준 검색, 페이징 지원)
    @Operation(summary = "로그 키워드 검색", description = "로그 제목으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO> searchLogList(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "9")  int size,
            @RequestParam(defaultValue = "최신순") String sort) {
        return ResponseEntity.ok(logService.getLogListByKeyword(keyword, page, size, sort));
    }

    // 카테고리별 로그 목록 조회 - GET /api/logs/category (페이징 지원)
    @Operation(summary = "카테고리 필터", description = "카테고리별 로그 목록을 조회합니다.")
    @GetMapping("/category")
    public ResponseEntity<ApiResponseDTO> getLogListByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "9")  int size,
            @RequestParam(defaultValue = "최신순") String sort) {
        return ResponseEntity.ok(logService.getLogListByCategory(category, page, size, sort));
    }

    // 내 로그 목록 전체 조회 - GET /api/logs/my-list (프로젝트 생성 모달용, JWT 필요)
    @Operation(summary = "내 로그 목록 조회", description = "로그인한 사용자의 로그 목록을 전체 반환합니다.")
    @GetMapping("/my-list")
    public ResponseEntity<ApiResponseDTO> getMyLogList(Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.getMyLogList(memberDTO.getId()));
    }

    // 휴지통 로그 목록 조회 - GET /api/logs/trashed
    @Operation(summary = "휴지통 로그 목록 조회", description = "로그인한 사용자의 휴지통(소프트 삭제된) 로그 목록을 반환합니다.")
    @GetMapping("/trashed")
    public ResponseEntity<ApiResponseDTO> getTrashedLogList(Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.getTrashedLogList(memberDTO.getId()));
    }

    // 로그 작성
    @Operation(summary = "로그 작성", description = "새로운 페일로그를 작성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponseDTO> createLog(@RequestBody LogCreateRequestDTO dto,
                                                    Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.createLog(dto, memberDTO.getId()));
    }

    // 로그 수정
    @Operation(summary = "로그 수정", description = "임시저장 상태인 페일로그를 덮어쓰기합니다.")
    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> updateLog(@PathVariable Long id,
                                                    @RequestBody com.app.springapp.domain.dto.request.LogUpdateRequestDTO dto,
                                                    Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.updateLog(id, dto, memberDTO.getId()));
    }

    @Operation(summary = "로그 상세 조회", description = "로그 ID로 단건 조회합니다. 쿠키 기반 중복 조회를 방지하며 조회수를 1 증가시킵니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getLog(@PathVariable Long id,
                                                 jakarta.servlet.http.HttpServletRequest request,
                                                 jakarta.servlet.http.HttpServletResponse response,
                                                 Authentication authentication) {
        Long memberId = null;
        if (authentication != null && authentication.getPrincipal() instanceof MemberDTO) {
            memberId = ((MemberDTO) authentication.getPrincipal()).getId();
        }

        boolean shouldIncreaseReadCount = true;
        String cookieName = "viewed_log_basic_" + id + (memberId != null ? "_" + memberId : "_guest");

        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    shouldIncreaseReadCount = false;
                    break;
                }
            }
        }

        if (shouldIncreaseReadCount) {
            org.springframework.http.ResponseCookie newCookie = org.springframework.http.ResponseCookie.from(cookieName, "true")
                    .maxAge(60 * 60 * 24)
                    .path("/")
                    .sameSite("Lax")
                    .secure(false)
                    .httpOnly(true)
                    .build();
            response.addHeader(org.springframework.http.HttpHeaders.SET_COOKIE, newCookie.toString());
        }

        return ResponseEntity.ok(logService.getLog(id, memberId, shouldIncreaseReadCount));
    }

    // 인기 솔루션 조회 (좋아요 10개 이상, 로그 목록 페이지 노출용)
    @Operation(summary = "인기 솔루션 조회", description = "좋아요 10개 이상인 로그의 분석 결과를 반환합니다.")
    @GetMapping("/popular-solutions")
    public ResponseEntity<ApiResponseDTO> getPopularSolutions() {
        return ResponseEntity.ok(logService.getPopularSolutions());
    }

    // 로그 좋아요 토글
    @Operation(summary = "로그 좋아요 토글", description = "특정 로그의 좋아요를 추가하거나 취소합니다.")
    @PostMapping("/like/{logId}")
    public ResponseEntity<ApiResponseDTO> toggleLike(@PathVariable Long logId, Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.toggleLike(logId, memberDTO.getId()));
    }

    // 로그 다중 삭제 (휴지통으로 이동)
    @Operation(summary = "로그 다중 소프트 삭제", description = "여러 로그를 휴지통으로 이동(소프트 삭제)합니다.")
    @DeleteMapping("/trash")
    public ResponseEntity<ApiResponseDTO> deleteLogs(@RequestBody java.util.List<Long> ids, Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.deleteLogs(ids, memberDTO.getId()));
    }

    // 로그 복원
    @Operation(summary = "로그 다중 복원", description = "휴지통에 있는 로그들을 복원합니다.")
    @PostMapping("/restore")
    public ResponseEntity<ApiResponseDTO> restoreLogs(@RequestBody java.util.List<Long> ids, Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.restoreLogs(ids, memberDTO.getId()));
    }

    // 로그 영구 삭제
    @Operation(summary = "로그 다중 영구 삭제", description = "여러 로그를 DB에서 영구 삭제합니다.")
    @DeleteMapping("/permanent")
    public ResponseEntity<ApiResponseDTO> hardDeleteLogs(@RequestBody java.util.List<Long> ids, Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.hardDeleteLogs(ids, memberDTO.getId()));
    }
}