package com.app.springapp.domain.dto.response;

import com.app.springapp.domain.vo.LogActionPlanVO;
import com.app.springapp.domain.vo.LogPatternVO;
import com.app.springapp.domain.vo.LogResultVO;
import com.app.springapp.domain.vo.RadarScoreVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class LogAnalyzeResultResponseDTO {
    private LogResponseDTO logInfo;
    private LogResultVO aiResult;
    private List<RadarScoreVO> radarScores;
    private List<LogPatternVO> logPatterns;
    private List<LogActionPlanVO> logActionPlans;
}
