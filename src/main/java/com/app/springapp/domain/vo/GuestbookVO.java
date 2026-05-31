package com.app.springapp.domain.vo;

import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookVO {

    private Long id;
    private String guestbookContent;
    private String guestbookCreatedAt;
    private Long ownerMemberId;
    private Long writerMemberId;
}
