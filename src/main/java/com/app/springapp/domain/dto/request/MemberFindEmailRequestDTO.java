package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "아이디(이메일) 찾기 요청 DTO")
public class MemberFindEmailRequestDTO {

    @Schema(description = "이름", example = "홍길동", required = true)
    private String memberName;

    @Schema(description = "전화번호", example = "010-1234-5678", required = true)
    private String memberPhone;
}