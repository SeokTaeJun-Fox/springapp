package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class GuestbookReplyVO {
   private Long id;
   private String guestbookReplyContent;
   private String guestbookReplyCreatedAt;
   private Long guestbookId;
}