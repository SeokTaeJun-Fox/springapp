package com.app.springapp.mapper;

import com.app.springapp.domain.vo.LogPatternVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LogPatternMapper {
    void insert(LogPatternVO logPatternVO);
    List<LogPatternVO> findByLogResultId(Long logResultId);
}
