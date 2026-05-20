package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class LogListServiceTests {

    @Autowired
    private LogService logService;

    @Test
    public void selectLogListTest() {
        ApiResponseDTO<List<LogListResponseDTO>> result = logService.getLogList(0, 10, "최신순");
        List<LogListResponseDTO> logList = (List<LogListResponseDTO>) result.getData();
        logList.forEach(item -> log.info(item.toString()));
    }
}