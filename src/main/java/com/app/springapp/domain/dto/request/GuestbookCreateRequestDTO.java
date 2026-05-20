package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "방명록 작성 요청 DTO")
public class GuestbookCreateRequestDTO {

    @Schema(description = "방명록 주인 회원 ID", example = "1", required = true)
    private Long ownerMemberId;

    @Schema(description = "방명록 내용 (최대 1000자)", example = "항상 좋은 글 감사합니다!", required = true)
    private String guestbookContent;
}