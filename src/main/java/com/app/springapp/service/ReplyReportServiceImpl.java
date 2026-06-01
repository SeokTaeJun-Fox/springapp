package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyReportCreateRequestDTO;
import com.app.springapp.repository.ReplyReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class ReplyReportServiceImpl implements ReplyReportService {

    private final ReplyReportDAO replyReportDAO;

    //댓글 신고 작성
    @Override
    public void write(ReplyReportCreateRequestDTO replyReportCreateRequestDTO) {
        replyReportDAO.save(replyReportCreateRequestDTO);
    }
}
