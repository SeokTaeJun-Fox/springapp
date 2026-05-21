package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "방명록 댓글 응답 DTO")
public class GuestbookReplyResponseDTO {

    @Schema(description = "방명록 댓글 ID", example = "1")
    private Long id;

    @Schema(description = "방명록 댓글 내용", example = "감사합니다!")
    private String guestbookReplyContent;

    @Schema(description = "작성 일시", example = "2024-01-01T11:00:00")
    private String guestbookReplyCreatedAt;

    @Schema(description = "방명록 ID", example = "1")
    private Long guestbookId;

    @Schema(description = "작성자 회원 ID", example = "1")
    private Long writerMemberId;

    @Schema(description = "작성자 닉네임", example = "길동이")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "좋아요 수", example = "2")
    private int likeCount;

    @Schema(description = "좋아요 여부 (1: 좋아요, 0: 미클릭)", example = "0")
    private int isLike;
}