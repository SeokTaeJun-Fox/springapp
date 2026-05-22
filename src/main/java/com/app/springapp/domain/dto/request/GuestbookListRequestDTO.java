package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "방명록 목록 조회 요청 DTO")
public class GuestbookListRequestDTO {
    @Schema(description = "프로필 주인 회원 ID", example = "1", required = true)
    private Long ownerMemberId;
}
