package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//ID              NUMBER          CONSTRAINT PK_RADAR_SCORE PRIMARY KEY,
//RADAR_COMPONENT VARCHAR2(255)   NOT NULL,
//RADAR_SCORE     NUMBER          NOT NULL,
//LOG_RESULT_ID   NUMBER          NOT NULL,
//CONSTRAINT FK_RADAR_SCORE_RESULT FOREIGN KEY (LOG_RESULT_ID) REFERENCES TBL_LOG_RESULT(ID)

@Component
@Data
public class RadarScoreVO {
    private Long id;
    private String radarComponent;
    private int radarScore; //수치가 어느정도인지 잘 모르지만 일단 int로 함
    private Long logResultId;
}
