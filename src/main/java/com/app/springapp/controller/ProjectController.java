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

// 프로젝트 관련 요청을 처리하는 컨트롤러 (생성, 조회, 수정, 삭제) - 기본 URL: /api/project
@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 생성 - POST /api/project/create
    @Operation(summary = "프로젝트 생성 (AI)", description = "로그 ID를 받아 AI가 프로젝트를 생성합니다.")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> createProject(
            Authentication authentication,
            @RequestBody ProjectCreateRequestDTO requestDTO) {
        ProjectResponseDTO response = projectService.createProjectByAI(requestDTO, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 생성되었습니다.", response));
    }

    // 다른 사람들의 프로젝트 목록 조회 - GET /api/project/others
    @Operation(summary = "다른 사람들의 프로젝트 목록 조회")
    @GetMapping("/others")
    public ResponseEntity<ApiResponseDTO<?>> getOtherProjects(Authentication authentication) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getOtherProjects(getMemberId(authentication))));
    }

    // 내 프로젝트 목록 조회 - GET /api/project/list
    @Operation(summary = "내 프로젝트 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO<?>> getMyProjects(Authentication authentication) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getMyProjects(getMemberId(authentication))));
    }

    // 프로젝트 단건 조회 - GET /api/project/{projectId}
    @Operation(summary = "프로젝트 단건 조회")
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> getProject(
            Authentication authentication,
            @PathVariable Long projectId) {
        ProjectResponseDTO response = projectService.getProject(projectId, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공", response));
    }

    // 프로젝트 삭제 - DELETE /api/project/{projectId}
    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> deleteProject(
            Authentication authentication,
            @PathVariable Long projectId) {
        projectService.deleteProject(projectId, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 삭제되었습니다."));
    }

    // 프로젝트 수정 - PUT /api/project/{projectId}
    @Operation(summary = "프로젝트 수정")
    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> updateProject(
            Authentication authentication,
            @PathVariable Long projectId,
            @RequestBody ProjectVO projectVO) {
        projectService.updateProject(projectId, getMemberId(authentication), projectVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 수정되었습니다."));
    }

    // 다른 사람 프로젝트 공개 단건 조회 - GET /api/project/public/{projectId}
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
            Authentication authentication,
            @PathVariable Long projectId) {
        projectService.copyProject(projectId, getMemberId(authentication));
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 내 목록에 추가되었습니다."));
    }

    // 프로젝트 제목으로 검색 - GET /api/project/search?keyword=검색어
    @Operation(summary = "프로젝트 검색", description = "프로젝트 제목으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<?>> searchOtherProjects(
            Authentication authentication,
            @RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "검색 성공",
                projectService.searchOtherProjects(getMemberId(authentication), keyword)));
    }

    private Long getMemberId(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof MemberDTO)) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        return ((MemberDTO) authentication.getPrincipal()).getId();
    }
}