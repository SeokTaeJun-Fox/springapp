package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookRereplyVO {

    private Long id;
    private String guestbookRereplyContent;
    private String guestbookRereplyCreatedAt;
    private Long guestbookReplyId;
    private Long writerMemberId;

}
