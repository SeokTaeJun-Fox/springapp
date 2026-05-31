package com.app.springapp.domain.dto.response;

import com.app.springapp.domain.vo.GuestbookReplyVO;
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

    @Schema(description = "대댓글 목록")
    private java.util.List<GuestbookRereplyResponseDTO> rereplies;

    // 파라미터로 직접 생성
    public static GuestbookReplyResponseDTO of(Long id, String guestbookReplyContent, String guestbookReplyCreatedAt,
                                               Long guestbookId, Long writerMemberId, String writerNickname,
                                               String writerProfileImageUrl, int likeCount, int isLike) {
        GuestbookReplyResponseDTO guestbookReplyResponseDTO = new GuestbookReplyResponseDTO();
        guestbookReplyResponseDTO.id = id;
        guestbookReplyResponseDTO.guestbookReplyContent = guestbookReplyContent;
        guestbookReplyResponseDTO.guestbookReplyCreatedAt = guestbookReplyCreatedAt;
        guestbookReplyResponseDTO.guestbookId = guestbookId;
        guestbookReplyResponseDTO.writerMemberId = writerMemberId;
        guestbookReplyResponseDTO.writerNickname = writerNickname;
        guestbookReplyResponseDTO.writerProfileImageUrl = writerProfileImageUrl;
        guestbookReplyResponseDTO.likeCount = likeCount;
        guestbookReplyResponseDTO.isLike = isLike;
        return guestbookReplyResponseDTO;
    }

    // VO → DTO 변환
    public static GuestbookReplyResponseDTO from(GuestbookReplyVO guestbookReplyVO) {
        GuestbookReplyResponseDTO guestbookReplyResponseDTO = new GuestbookReplyResponseDTO();
        guestbookReplyResponseDTO.id = guestbookReplyVO.getId();
        guestbookReplyResponseDTO.guestbookReplyContent = guestbookReplyVO.getGuestbookReplyContent();
        guestbookReplyResponseDTO.guestbookReplyCreatedAt = guestbookReplyVO.getGuestbookReplyCreatedAt();
        guestbookReplyResponseDTO.guestbookId = guestbookReplyVO.getGuestbookId();
        guestbookReplyResponseDTO.writerMemberId = guestbookReplyVO.getWriterMemberId();
        return guestbookReplyResponseDTO;
    }
}