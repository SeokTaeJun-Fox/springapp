package com.app.springapp.repository;

import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import com.app.springapp.mapper.RereplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RereplyDAO {

    private final RereplyMapper rereplyMapper;

    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> findAll(Long id) {
        return rereplyMapper.selectAll(id);
    }
}
