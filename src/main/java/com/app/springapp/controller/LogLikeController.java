package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.LogLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그 좋아요 API", description = "페일로그 좋아요 토글 / 좋아요 목록 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
public class LogLikeController {

    private final LogLikeService logLikeService;

    @Operation(summary = "좋아요 토글", description = "좋아요가 없으면 추가, 있으면 취소합니다.")
    @PostMapping("/{logId}/like")
    public ResponseEntity<ApiResponseDTO> toggleLike(
            @PathVariable Long logId,
            Authentication authentication) {
        MemberDTO member = (MemberDTO) authentication.getPrincipal();
        logLikeService.toggleLike(logId, member.getId());
        return ResponseEntity.ok(ApiResponseDTO.of(true, "좋아요 토글 완료"));
    }

    @Operation(summary = "좋아요한 로그 목록 조회", description = "로그인한 사용자가 좋아요한 페일로그 목록을 반환합니다.")
    @GetMapping("/liked-list")
    public ResponseEntity<ApiResponseDTO> getLikedLogs(Authentication authentication) {
        MemberDTO member = (MemberDTO) authentication.getPrincipal();
        return ResponseEntity.ok(ApiResponseDTO.of(true, "좋아요 목록 조회 성공",
                logLikeService.getLikedLogs(member.getId())));
    }
}
