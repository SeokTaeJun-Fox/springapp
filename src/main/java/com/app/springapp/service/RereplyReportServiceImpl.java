package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyReportCreateRequestDTO;
import com.app.springapp.repository.RereplyReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RereplyReportServiceImpl implements RereplyReportService {

    private final RereplyReportDAO rereplyReportDAO;

    //대댓글 신고 작성
    @Override
    public void write(RereplyReportCreateRequestDTO rereplyReportCreateRequestDTO) {
        rereplyReportDAO.save(rereplyReportCreateRequestDTO);
    }
}
