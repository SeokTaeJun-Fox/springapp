package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "방명록 목록 조회 응답 DTO")
public class GuestbookListResponseDTO {

    @Schema(description = "방명록 ID", example = "1", required = true)
    private Long id;
    @Schema(description = "방명록 게시글 내용", example = "안녕하세요.", required = true)
    private String guestbookContent;
    @Schema(description = "방명록 주인 멤버 ID", example = "1", required = true)
    private Long ownerMemberId;
    @Schema(description = "방명록 작성자 ID", example = "2", required = true)
    private Long writerMemberId;
    @Schema(description = "방명록 작성자 닉네임", example = "감내하는사람", required = true)
    private String writerNickname;

}
