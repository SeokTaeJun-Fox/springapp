package com.app.springapp.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "커뮤니티 답글 좋아요 요청 DTO")
public class ReplyLikeRequestDTO {

    @Schema(description = "좋아요를 누른 멤버ID", example = "1", required = true)
    private Long memberId;

    @Schema(description = "좋아요 눌러진 답글ID", example = "1", required = true)
    private Long replyId;
}
