package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.ReplyReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyReportMapper {
    public void insert(ReplyReportCreateRequestDTO replyReportCreateRequestDTO);
}
