package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GuestbookLikeVO {
    private Long id;
    private Long guestbookId;
    private Long memberId;
}