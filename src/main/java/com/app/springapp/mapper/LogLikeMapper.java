package com.app.springapp.mapper;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.vo.LogLikeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogLikeMapper {
    void insert(LogLikeVO logLikeVO);
    void delete(LogLikeVO logLikeVO);
    int isLiked(LogLikeVO logLikeVO);
    List<LogListResponseDTO> selectLikedLogsByMemberId(Long memberId);
}
