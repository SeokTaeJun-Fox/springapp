package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;

public interface ChronologyService {

    ChronologyAnalysisResponseDTO getAnalysis(Long projectId);

    void generateAiFeedbackAsync(Long projectId, ChronologyAnalysisResponseDTO dto);

    String getFeedback(Long projectId);
}
