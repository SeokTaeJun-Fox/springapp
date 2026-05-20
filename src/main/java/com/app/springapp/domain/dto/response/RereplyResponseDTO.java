package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "대댓글 응답 DTO")
public class RereplyResponseDTO {

    @Schema(description = "대댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 ID", example = "1")
    private Long replyId;

    @Schema(description = "대댓글 내용", example = "저도 동의해요!")
    private String rereplyContent;

    @Schema(description = "작성 일시", example = "2024-01-01T11:30:00")
    private String rereplyCreatedAt;

    @Schema(description = "작성자 ID", example = "3")
    private Long memberId;

    @Schema(description = "작성자 닉네임", example = "성장중")
    private String memberNickname;
}
