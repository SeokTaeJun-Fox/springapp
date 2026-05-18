package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "커뮤니티 게시글 검색 응답")
public class SearchPostResponseDTO {

    @Schema(description = "검색 게시글 갯수" ,examples="100")
    private String postCount;

    @Schema(description = "게시글 목록")
    private List<PostResponseDTO> posts;
}
