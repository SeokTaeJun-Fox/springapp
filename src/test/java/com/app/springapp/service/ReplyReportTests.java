package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyReportCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ReplyReportTests {

    @Autowired
    ReplyReportService replyReportService;

    @Test
    public void writeTest() {
        ReplyReportCreateRequestDTO postReportCreateRequestDTO  = new ReplyReportCreateRequestDTO();
        postReportCreateRequestDTO.setReplyReportContent("테스트 신고22");
        postReportCreateRequestDTO.setReportReasonCategoryId(2L);
        postReportCreateRequestDTO.setReplyId(1L);
        postReportCreateRequestDTO.setMemberId(1L);
        replyReportService.write(postReportCreateRequestDTO);
    }
}
