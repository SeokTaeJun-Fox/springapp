package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookRereplyCreateRequestDTO {

    @Schema(description = "방명록 대댓글 ID" , example = "1", hidden = true)
    private Long id;
    @Schema(description = "방명록 댓글 ID", example = "1", required = true)
    private Long guestbookReplyId;
    @Schema(description = "방명록 대댓글 내용", example = "저도 반가워요", required = true)
    private String guestbookRereplyContent;
    @Schema(description = "방명록 대댓글 작성자 id", example = "2", required = true)
    private Long writerMemberId;

}
