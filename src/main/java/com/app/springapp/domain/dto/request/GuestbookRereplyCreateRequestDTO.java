package com.app.springapp.domain.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookRereplyCreateRequestDTO {

    public Long id;
    private Long guestbookReplyId;
    private String guestbookRereplyContent;
    private Long writerMemberId;

}
