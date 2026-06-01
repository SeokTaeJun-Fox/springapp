package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostReportMapper {
    public void insert(PostReportCreateRequestDTO postReportCreateRequestDTO);
}
