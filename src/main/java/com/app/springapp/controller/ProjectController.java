package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.ProjectCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.ProjectResponseDTO;
import com.app.springapp.domain.vo.ProjectVO;
import com.app.springapp.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 프로젝트 관련 요청을 처리하는 컨트롤러 (생성, 조회, 수정, 삭제) - 기본 URL: /api/project
@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 생성 - POST /api/project/create (AI가 로그 분석 결과 기반으로 자동 생성, 로그인 구현 전 임시로 memberId 1L 고정)
    @Operation(summary = "프로젝트 생성 (AI)", description = "로그 ID를 받아 AI가 프로젝트를 생성합니다.")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> createProject(
            @RequestBody ProjectCreateRequestDTO requestDTO) {
        ProjectResponseDTO response = projectService.createProjectByAI(requestDTO, 1L); // 로그인 구현 후 memberVO.getId()로 교체
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 생성되었습니다.", response));
    }

    // 다른 사람들의 프로젝트 목록 조회 - GET /api/project/others
    @Operation(summary = "다른 사람들의 프로젝트 목록 조회")
    @GetMapping("/others")
    public ResponseEntity<ApiResponseDTO<?>> getOtherProjects() {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getOtherProjects(1L))); // 로그인 구현 후 memberVO.getId()로 교체
    }

    // 내 프로젝트 목록 조회 - GET /api/project/list (프로젝트 메인 페이지 캐러셀용, 로그인 구현 전 임시로 memberId 1L 고정)
    @Operation(summary = "내 프로젝트 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO<?>> getMyProjects() {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공",
                projectService.getMyProjects(1L))); // 로그인 구현 후 memberVO.getId()로 교체
    }

    // 프로젝트 단건 조회 - GET /api/project/{projectId} (상세 페이지 진입 시, 로그인 구현 전 임시로 memberId 1L 고정)
    @Operation(summary = "프로젝트 단건 조회")
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<ProjectResponseDTO>> getProject(
            @PathVariable Long projectId) {
        ProjectResponseDTO response = projectService.getProject(projectId, 1L); // 로그인 구현 후 memberVO.getId()로 교체
        return ResponseEntity.ok(ApiResponseDTO.of(true, "조회 성공", response));
    }

    // 프로젝트 삭제 - DELETE /api/project/{projectId} (상세 페이지에서 삭제 버튼 클릭 시, 로그인 구현 전 임시로 memberId 1L 고정)
    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> deleteProject(
            @PathVariable Long projectId) {
        projectService.deleteProject(projectId, 1L); // 로그인 구현 후 memberVO.getId()로 교체
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 삭제되었습니다."));
    }

    // 프로젝트 수정 - PUT /api/project/{projectId} (상세 페이지에서 수정 버튼 클릭 시, 로그인 구현 전 임시로 memberId 1L 고정)
    @Operation(summary = "프로젝트 수정")
    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponseDTO<?>> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectVO projectVO) {
        projectService.updateProject(projectId, 1L, projectVO); // 로그인 구현 후 memberVO.getId()로 교체
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 수정되었습니다."));
    }

    // 다른 사람 프로젝트 공개 단건 조회 - GET /api/project/public/{projectId}
    // 소유자 검증 없이 누구나 조회 가능
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
            @PathVariable Long projectId) {
        projectService.copyProject(projectId, 1L); // 로그인 구현 후 memberVO.getId()로 교체
        return ResponseEntity.ok(ApiResponseDTO.of(true, "프로젝트가 내 목록에 추가되었습니다."));
    }

    // 프로젝트 제목으로 검색 - GET /api/project/search?keyword=검색어
    @Operation(summary = "프로젝트 검색", description = "프로젝트 제목으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<?>> searchOtherProjects(
            @RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponseDTO.of(true, "검색 성공",
                projectService.searchOtherProjects(1L, keyword))); // 로그인 구현 후 memberVO.getId()로 교체
    }
}