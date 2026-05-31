package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.ProfileVisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "프로필 방문자 API", description = "방문 기록 저장 / 오늘 방문자 수 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/private/profile")
public class ProfileVisitController {

    private final ProfileVisitService profileVisitService;

    @Operation(summary = "방문 기록", description = "타인의 프로필을 방문할 때 호출합니다. 자기 자신은 기록되지 않습니다.")
    @PostMapping("/{ownerId}/visit")
    public ResponseEntity<ApiResponseDTO> recordVisit(
            @PathVariable Long ownerId,
            Authentication authentication) {
        MemberDTO visitor = (MemberDTO) authentication.getPrincipal();
        profileVisitService.recordVisit(ownerId, visitor.getId());
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방문 기록 완료"));
    }

    @Operation(summary = "오늘 방문자 수 조회", description = "내 프로필의 오늘 방문자 수를 반환합니다.")
    @GetMapping("/{ownerId}/today-visitors")
    public ResponseEntity<ApiResponseDTO> getTodayVisitors(
            @PathVariable Long ownerId,
            Authentication authentication) {
        int count = profileVisitService.getTodayVisitorCount(ownerId);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "오늘 방문자 수 조회 성공", count));
    }
}
