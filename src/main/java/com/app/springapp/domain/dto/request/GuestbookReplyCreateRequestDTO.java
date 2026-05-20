package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "방명록 댓글 작성 요청 DTO")
public class GuestbookReplyCreateRequestDTO {

    @Schema(description = "방명록 ID", example = "1", required = true)
    private Long guestbookId;

    @Schema(description = "댓글 내용 (최대 500자)", example = "감사합니다!", required = true)
    private String guestbookReplyContent;
}