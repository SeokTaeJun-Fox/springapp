package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;

//게시글 신고
public interface PostReportService {
    public void write(PostReportCreateRequestDTO postReportCreateRequestDTO);
}
