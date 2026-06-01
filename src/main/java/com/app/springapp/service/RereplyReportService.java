package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyReportCreateRequestDTO;

//대댓글 신고
public interface RereplyReportService {
    public void write(RereplyReportCreateRequestDTO rereplyReportCreateRequestDTO);
}
