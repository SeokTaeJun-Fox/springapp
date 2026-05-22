package com.app.springapp.mapper;

import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RereplyMapper {
    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> selectAll(Long id);
}
