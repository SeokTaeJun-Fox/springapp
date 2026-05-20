package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ProjectCreateRequestDTO;
import com.app.springapp.domain.dto.response.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    ProjectResponseDTO createProjectByAI(ProjectCreateRequestDTO requestDTO, Long memberId);
    List<ProjectResponseDTO> getMyProjects(Long memberId);
    ProjectResponseDTO getProject(Long projectId, Long memberId);
    void deleteProject(Long projectId, Long memberId);
}