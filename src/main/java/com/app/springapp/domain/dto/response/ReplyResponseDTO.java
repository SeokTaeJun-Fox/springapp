package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "댓글 응답 DTO")
public class ReplyResponseDTO {

    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "댓글 내용", example = "공감합니다! 저도 같은 경험이 있어요.")
    private String replyContent;

    @Schema(description = "작성 일시", example = "2024-01-01T11:00:00")
    private String replyCreatedAt;

    @Schema(description = "작성자 ID", example = "2")
    private Long memberId;

    @Schema(description = "작성자 닉네임", example = "개발러")
    private String memberNickname;

    @Schema(description = "좋아요 수", example = "3")
    private int likeCount;
}
