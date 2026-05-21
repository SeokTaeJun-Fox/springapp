package com.app.springapp.service;

import com.app.springapp.repository.PostPictureDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PostPictureTests {

    @Autowired
    private PostPictureService postPictureService;

    @Test
    public void findAllTest() {
        log.info("find : {}", postPictureService.findAll(1L));
    }
}
