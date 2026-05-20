package com.app.springapp.repository;

import com.app.springapp.domain.vo.LogResultVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogResultDAO {
    LogResultVO findByLogId(Long logId);
    LogResultVO findById(Long id);
}