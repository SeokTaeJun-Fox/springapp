package com.app.springapp.mapper;

import com.app.springapp.domain.vo.RadarScoreVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RadarScoreMapper {
    void insert(RadarScoreVO radarScoreVO);
    List<RadarScoreVO> findByLogResultId(Long logResultId);
}
