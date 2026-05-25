package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.ChecklistCreateRequestDTO;
import com.app.springapp.domain.dto.request.ChecklistUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.ChecklistResponseDTO;
import com.app.springapp.service.ChecklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.springapp.domain.dto.MemberDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

// 체크리스트 관련 요청을 처리하는 컨트롤러 (추가, 조회, 수정, 삭제) - 기본 URL: /api/checklist
@RestController
@RequestMapping("/api/checklist")
@RequiredArgsConstructor
@Tag(name = "Checklist", description = "체크리스트 API")
public class ChecklistController {

    private final ChecklistService checklistService;

    private Long getMemberId(Authentication authentication) {
        return ((MemberDTO) authentication.getPrincipal()).getId();
    }

    // 체크리스트 추가
    @Operation(summary = "체크리스트 추가")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<ChecklistResponseDTO>> createChecklist(
            @RequestBody ChecklistCreateRequestDTO requestDTO,
            Authentication authentication) {
        ChecklistResponseDTO response = checklistService.createChecklist(requestDTO, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "체크리스트가 추가되었습니다.", response));
    }

    // 프로젝트별 체크리스트 목록 조회
    @Operation(summary = "체크리스트 목록 조회")
    @GetMapping("/list/{projectId}")
    public ResponseEntity<ApiResponseDTO<List<ChecklistResponseDTO>>> getChecklists(
            @PathVariable Long projectId) {
        List<ChecklistResponseDTO> response = checklistService.getChecklists(projectId);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공", response));
    }

    // 체크리스트 수정
    @Operation(summary = "체크리스트 수정")
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO<ChecklistResponseDTO>> updateChecklist(
            @RequestBody ChecklistUpdateRequestDTO requestDTO) {
        ChecklistResponseDTO response = checklistService.updateChecklist(requestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "체크리스트가 수정되었습니다.", response));
    }

    // 체크리스트 삭제
    @Operation(summary = "체크리스트 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteChecklist(
            @PathVariable Long id) {
        checklistService.deleteChecklist(id);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "체크리스트가 삭제되었습니다."));
    }
}