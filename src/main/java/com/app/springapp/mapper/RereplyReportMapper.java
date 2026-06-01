package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.RereplyReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RereplyReportMapper {
    public void insert(RereplyReportCreateRequestDTO rereplyReportCreateRequestDTO);
}
