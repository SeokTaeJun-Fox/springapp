package com.app.springapp.api.privateapi;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;
import com.app.springapp.service.ChronologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "성장 연대기 비공개 API", description = "성과 분석 (JWT 필요)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/private/chronology")
public class ChronologyPrivateApi {

    private final ChronologyService chronologyService;

    @Operation(summary = "성과 분석 조회", description = "프로젝트 ID로 성과 분석 데이터를 즉시 반환합니다. AI 피드백은 별도 폴링으로 수신하세요.")
    @GetMapping("/analysis/{projectId}")
    public ResponseEntity<ApiResponseDTO<ChronologyAnalysisResponseDTO>> getAnalysis(
            @PathVariable Long projectId) {
        ChronologyAnalysisResponseDTO result = chronologyService.getAnalysis(projectId);
        // AI 피드백 비동기 생성 트리거 (같은 빈에서 호출하면 @Async 동작 안 하므로 컨트롤러에서 호출)
        chronologyService.generateAiFeedbackAsync(projectId, result);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "성과 분석 조회 성공", result));
    }

    @Operation(summary = "AI 피드백 폴링", description = "생성 완료 시 success=true, 생성 중이면 success=false 반환")
    @GetMapping("/feedback/{projectId}")
    public ResponseEntity<ApiResponseDTO<String>> getFeedback(@PathVariable Long projectId) {
        String feedback = chronologyService.getFeedback(projectId);
        if (feedback != null) {
            return ResponseEntity.ok(ApiResponseDTO.of(true, "피드백 준비 완료", feedback));
        }
        return ResponseEntity.ok(ApiResponseDTO.of(false, "피드백 생성 중", null));
    }
}
