package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyReportCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RereplyReportTests {

    @Autowired
    RereplyReportService rereplyReportService;

    @Test
    public void writeTest() {
        RereplyReportCreateRequestDTO rereplyReportCreateRequestDTO = new RereplyReportCreateRequestDTO();
        rereplyReportCreateRequestDTO.setRereplyReportContent("테스트 신고22");
        rereplyReportCreateRequestDTO.setReportReasonCategoryId(2L);
        rereplyReportCreateRequestDTO.setRereplyId(1L);
        rereplyReportCreateRequestDTO.setMemberId(1L);
        rereplyReportService.write(rereplyReportCreateRequestDTO);
    }
}
