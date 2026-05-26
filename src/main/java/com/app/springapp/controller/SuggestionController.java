package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.SuggestionCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.SuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestion")
@RequiredArgsConstructor
@Tag(name = "Suggestion", description = "제안 API")
public class SuggestionController {

    private final SuggestionService suggestionService;

    // 제안 작성 - 로그인한 회원의 ID를 JWT에서 추출하여 제안 등록
    @Operation(summary = "제안 작성")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<?>> createSuggestion(
            @RequestBody SuggestionCreateRequestDTO requestDTO,
            Authentication authentication) {
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        suggestionService.createSuggestion(requestDTO, memberDTO.getId());
        return ResponseEntity.ok(ApiResponseDTO.of(true, "제안이 등록되었습니다."));
    }

    // 제안 목록 조회 - 특정 프로젝트에 달린 제안 목록 반환
    @Operation(summary = "프로젝트별 제안 목록 조회")
    @GetMapping("/list/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> getSuggestions(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                suggestionService.getSuggestionsByProjectId(projectId)));
    }
}