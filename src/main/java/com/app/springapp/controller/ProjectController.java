package com.app.springapp.controller;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.ProjectCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.ProjectResponseDTO;
import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 생성 (AI)", description = "로그 ID를 받아 AI가 프로젝트를 생성합니다.")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> createProject(
            @RequestBody ProjectCreateRequestDTO requestDTO,
            Authentication authentication) {
        ProjectResponseDTO response = projectService.createProjectByAI(requestDTO, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 생성되었습니다.", response));
    }

    @Operation(summary = "다른 사람들의 프로젝트 목록 조회")
    @GetMapping("/others")
    public ResponseEntity<ApiResponseDTO<?>> getOtherProjects(Authentication authentication) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getOtherProjects(getMemberId(authentication))));
    }

    @Operation(summary = "내 프로젝트 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO<?>> getMyProjects(Authentication authentication) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getMyProjects(getMemberId(authentication))));
    }

    @Operation(summary = "프로젝트 단건 조회")
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> getProject(
            @PathVariable Long projectId,
            Authentication authentication) {
        ProjectResponseDTO response = projectService.getProject(projectId, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공", response));
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> deleteProject(
            @PathVariable Long projectId,
            Authentication authentication) {
        projectService.deleteProject(projectId, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 삭제되었습니다."));
    }

    @Operation(summary = "프로젝트 수정")
    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectVO projectVO,
            Authentication authentication) {
        projectService.updateProject(projectId, getMemberId(authentication), projectVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 수정되었습니다."));
    }

    @Operation(summary = "프로젝트 공개 단건 조회", description = "다른 사람의 프로젝트를 조회합니다.")
    @GetMapping("/public/{projectId}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> getProjectPublic(
            @PathVariable Long projectId) {
        ProjectResponseDTO response = projectService.getProjectPublic(projectId);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공", response));
    }

    // 다른 사람의 프로젝트 복사 - POST /api/project/copy/{projectId}
    @Operation(summary = "다른 사람의 프로젝트 복사", description = "다른 사람의 프로젝트를 내 프로젝트로 복사합니다.")
    @PostMapping("/copy/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> copyProject(
            @PathVariable Long projectId,
            @RequestParam Long logId,
            Authentication authentication) {
        Long newProjectId = projectService.copyProject(projectId, getMemberId(authentication), logId);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 내 목록에 추가되었습니다.", newProjectId));
    }

    @Operation(summary = "특정 회원의 프로젝트 목록 조회 (마이페이지 공개)")
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponseDTO<?>> getProjectsByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getMyProjects(memberId)));
    }

    @Operation(summary = "프로젝트 검색", description = "프로젝트 제목으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<?>> searchOtherProjects(
            @RequestParam String keyword,
            Authentication authentication) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "검색 성공",
                projectService.searchOtherProjects(getMemberId(authentication), keyword)));
    }

    private Long getMemberId(Authentication authentication) {
        return ((MemberDTO) authentication.getPrincipal()).getId();
    }
}