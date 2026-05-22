package com.app.springapp.service;

import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import com.app.springapp.repository.RereplyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RereplyServiceImpl implements RereplyService {

    private final RereplyDAO rereplyDAO;

    //댓글id로 대댓글 목록 보여준다
    @Override
    public List<RereplyResponseDTO> findAll(Long postId) {
        return rereplyDAO.findAll(postId);
    }
}
