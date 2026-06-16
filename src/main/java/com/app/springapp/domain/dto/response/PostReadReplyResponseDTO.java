package com.app.springapp.domain.dto.response;

import com.app.springapp.domain.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "게시글 상세보기의 댓글 DTO")
public class PostReadReplyResponseDTO {

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

    @Schema(description = "작성자 프로필 이미지 url", example = "https://example.com/image.jpg")
    private String memberProfileImageUrl;

    @Schema(description = "좋아요 수", example = "3")
    private int likeCount;

    @Schema(description = "좋아요 클릭 여부",
            example = "1",
            allowableValues = {"0", "1"},
            type = "integer",
            format = "int32",
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = "x-enum-values", value = "0: false(좋아요 안누름), 1: true(좋아요 누름)")
                    })
            })
    private int isLiked;

    @Schema(description = "대 댓글")
    List<RereplyResponseDTO> replies;

    public static PostReadReplyResponseDTO of(ReplyDTO replyDTO) {
        PostReadReplyResponseDTO replyResponseDTO = new PostReadReplyResponseDTO();
        replyResponseDTO.setId(replyDTO.getId());
        replyResponseDTO.setPostId(replyDTO.getPostId());
        replyResponseDTO.setReplyContent(replyDTO.getReplyContent());
        replyResponseDTO.setMemberId(replyDTO.getMemberId());
        replyResponseDTO.setReplyCreatedAt(replyDTO.getReplyCreatedAt());
        replyResponseDTO.setMemberNickname(replyDTO.getMemberNickname());
        replyResponseDTO.setMemberProfileImageUrl(replyDTO.getMemberProfileImageUrl());
        replyResponseDTO.setLikeCount(replyDTO.getLikeCount());
        replyResponseDTO.setIsLiked(replyDTO.getIsLiked());
        return replyResponseDTO;
    }
}
