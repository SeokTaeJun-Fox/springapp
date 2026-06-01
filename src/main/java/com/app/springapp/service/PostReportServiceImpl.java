package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import com.app.springapp.repository.PostReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostReportServiceImpl implements PostReportService {

    private final PostReportDAO postReportDAO;

    //게시글 신고 작성
    @Override
    public void write(PostReportCreateRequestDTO postReportCreateRequestDTO) {
        postReportDAO.save(postReportCreateRequestDTO);
    }
}
