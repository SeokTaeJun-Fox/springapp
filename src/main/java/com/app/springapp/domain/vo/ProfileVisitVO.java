package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
@Component
public class ProfileVisitVO {

     private Long id;
     private Long ownerMemberId;
     private Long visitorMemberId;
     private LocalDateTime visitedAt;

}
