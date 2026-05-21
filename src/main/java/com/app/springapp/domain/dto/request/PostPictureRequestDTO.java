package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "게시글 첨부 사진 요청DTO")
public class PostPictureRequestDTO {

    @Schema(description = "게시글 사진 번호", example = "1", required = true)
    private Integer postPictureSequence;
    @Schema(description = "사진 경로", example = "img.freepik.com/free-photo/stack-notebooks-cup-with-pencils_23-2147711407.jpg?semt=ais_hybrid&amp;w=740&amp;q=80", required = true)
    private String postPictureAddress;
    @Schema(description = "게시글ID", example = "1", required = true)
    private Long postId;
}
