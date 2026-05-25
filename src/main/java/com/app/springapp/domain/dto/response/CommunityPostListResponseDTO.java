package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "커뮤니티 게시글 목록 응답 DTO")
public class CommunityPostListResponseDTO {

    @Schema(description = "게시글 총 수", example = "100")
    private Integer total;

    @Schema(description = "한 페이지에 보여질 게시글들")
    private List<PostListResponseDTO> posts;
}
