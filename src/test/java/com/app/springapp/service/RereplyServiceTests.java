package com.app.springapp.service;

import com.app.springapp.domain.dto.request.RereplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyUpdateRequestDTO;
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
        log.info("findAll : {}", rereplyService.findAll(49L));
    }

    @Test
    public void findTest() {
        log.info("findAll : {}", rereplyService.find(2L));
    }

    @Test
    public void writeRereplyTest() {
        RereplyCreateRequestDTO rereplyCreateRequestDTO = new RereplyCreateRequestDTO();
        rereplyCreateRequestDTO.setReplyId(51L);
        rereplyCreateRequestDTO.setMemberId(4L);
        rereplyCreateRequestDTO.setRereplyContent("콘텐트 input");
        rereplyService.writeRereply(rereplyCreateRequestDTO);
    }

    @Test
    public void updateRereplyTest() {
        RereplyUpdateRequestDTO rereplyUpdateRequestDTO = new RereplyUpdateRequestDTO();
        rereplyUpdateRequestDTO.setId(74L);
        rereplyUpdateRequestDTO.setRereplyContent("또 수정된 테스트");
        rereplyService.updateRereply(rereplyUpdateRequestDTO);
    }

    @Test
    public void deleteRereplyTest() {
        rereplyService.deleteRereply(56L);
    }

    @Test
    public void deleteRereplyTestByRereplyId(){
        rereplyService.deleteByRereplyId(59L);
    }
}
