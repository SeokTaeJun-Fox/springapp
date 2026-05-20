package com.app.springapp.mapper;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {
    public List<LogListResponseDTO> selectLogList(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    public List<LogListResponseDTO> selectLogListByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    public List<LogListResponseDTO> selectLogListByCategory(
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    List<LogListResponseDTO> findAllByMemberId(Long memberId);

}