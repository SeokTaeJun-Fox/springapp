package com.app.springapp.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RereplyServiceTests {

    @Autowired
    private RereplyService rereplyService;

    @Test
    public void findAllTest() {
        log.info("findAll : {}", rereplyService.findAll(3L));
    }
}
