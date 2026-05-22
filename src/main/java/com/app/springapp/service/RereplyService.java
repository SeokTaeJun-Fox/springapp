package com.app.springapp.service;

import com.app.springapp.domain.dto.response.RereplyResponseDTO;

import java.util.List;

public interface RereplyService {
    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> findAll(Long postId);
}
