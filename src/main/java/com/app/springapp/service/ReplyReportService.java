package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyReportCreateRequestDTO;

//댓글 신고
public interface ReplyReportService {
    public void write(ReplyReportCreateRequestDTO replyReportCreateRequestDTO);
}
