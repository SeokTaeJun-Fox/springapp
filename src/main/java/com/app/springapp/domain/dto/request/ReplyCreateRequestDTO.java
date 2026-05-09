package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "댓글 작성 요청 DTO")
public class
ReplyCreateRequestDTO {

    @Schema(description = "게시글 ID", example = "1", required = true)
    private Long postId;

    @Schema(description = "댓글 내용", example = "공감합니다! 저도 같은 경험이 있어요.", required = true)
    private String replyContent;
}
