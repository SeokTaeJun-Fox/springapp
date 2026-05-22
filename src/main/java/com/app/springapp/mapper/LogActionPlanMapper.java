package com.app.springapp.mapper;

import com.app.springapp.domain.vo.LogActionPlanVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LogActionPlanMapper {
    void insert(LogActionPlanVO logActionPlanVO);
    List<LogActionPlanVO> findByLogResultId(Long logResultId);
}
