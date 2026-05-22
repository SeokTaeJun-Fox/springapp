package com.app.springapp.domain.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookRereplyResponseDTO {

    public Long id;
    public String guestbookRereplyContent;
    public String guestbookRereplyCreatedAt;
    public Long guestbookReplyId;
    public Long writerMemberId;
    public String writerNickname;
    public String writerProfileImageUrl;

}
