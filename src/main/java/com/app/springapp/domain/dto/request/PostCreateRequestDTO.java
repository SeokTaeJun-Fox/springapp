package com.app.springapp.domain.dto.request;

import com.app.springapp.domain.vo.PostPictureVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "커뮤니티 게시글 작성 요청 DTO")
public class PostCreateRequestDTO {

    @Schema(description = "게시글 제목", example = "실패에서 배운 점을 공유합니다", required = true)
    private String postTitle;

    @Schema(description = "게시글 내용", example = "이번 프로젝트에서 가장 크게 느낀 점은...", required = false)
    private String postContent;

    @Schema(description = "카테고리 ID", example = "1", required = true)
    private Long categoryId;

    @Schema(description = "첨부 이미지 목록")
    private List<PostPictureRequestDTO> postPictureRequestDTO;
}
