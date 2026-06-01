package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PostReportTests {

    @Autowired
    PostReportService postReportService;

    @Test
    public void writeTest() {
        PostReportCreateRequestDTO postReportCreateRequestDTO = new PostReportCreateRequestDTO();
        postReportCreateRequestDTO.setPostReportContent("테스트 신고2");
        postReportCreateRequestDTO.setReportReasonCategoryId(2L);
        postReportCreateRequestDTO.setPostId(1L);
        postReportCreateRequestDTO.setMemberId(1L);
        postReportService.write(postReportCreateRequestDTO);
    }
}
