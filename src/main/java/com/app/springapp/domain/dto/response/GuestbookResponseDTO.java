package com.app.springapp.domain.dto.response;

import com.app.springapp.domain.vo.GuestbookVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "방명록 응답 DTO")
public class GuestbookResponseDTO {

    @Schema(description = "방명록 ID", example = "1")
    private Long id;

    @Schema(description = "방명록 내용", example = "항상 좋은 글 감사합니다!")
    private String guestbookContent;

    @Schema(description = "작성 일시", example = "2024-01-01T10:00:00")
    private String guestbookCreatedAt;

    @Schema(description = "방명록 주인 회원 ID", example = "1")
    private Long ownerMemberId;

    @Schema(description = "방명록 주인 닉네임", example = "주인장")
    private String ownerNickname;

    @Schema(description = "작성자 회원 ID", example = "2")
    private Long writerMemberId;

    @Schema(description = "작성자 닉네임", example = "개발러")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "좋아요 수", example = "5")
    private int likeCount;

    @Schema(description = "좋아요 여부 (1: 좋아요, 0: 미클릭)", example = "0")
    private int isLike;

    @Schema(description = "방명록 댓글 목록")
    private List<GuestbookReplyResponseDTO> replies;

    // VO → DTO 변환
    public static GuestbookResponseDTO from(GuestbookVO guestbookVO) {
        GuestbookResponseDTO guestbookResponseDTO = new GuestbookResponseDTO();
        guestbookResponseDTO.id = guestbookVO.getId();
        guestbookResponseDTO.guestbookContent = guestbookVO.getGuestbookContent();
        guestbookResponseDTO.guestbookCreatedAt = guestbookVO.getGuestbookCreatedAt();
        guestbookResponseDTO.ownerMemberId = guestbookVO.getOwnerMemberId();
        guestbookResponseDTO.writerMemberId = guestbookVO.getWriterMemberId();
        return guestbookResponseDTO;
    }
}