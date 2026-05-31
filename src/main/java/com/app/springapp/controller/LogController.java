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
@Tag(name = "로그 API", description = "페일로그 목록 조회")
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

    // 로그 작성
    @PostMapping
    public ResponseEntity<ApiResponseDTO> createLog(@RequestBody LogCreateRequestDTO dto,
                                                    Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(logService.createLog(dto, memberDTO.getId()));
    }

    @Operation(summary = "로그 상세 조회", description = "로그 ID로 단건 조회 후 조회수를 증가합니다.")
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
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("viewed_log_basic_" + id)) {
                    shouldIncreaseReadCount = false;
                    break;
                }
            }
        }

        if (shouldIncreaseReadCount) {
            jakarta.servlet.http.Cookie newCookie = new jakarta.servlet.http.Cookie("viewed_log_basic_" + id, "true");
            newCookie.setMaxAge(60 * 60 * 24);
            newCookie.setPath("/");
            response.addCookie(newCookie);
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
}