package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Schema(description = "커뮤니티 메인 페이지 응답 (첫 진입시)")
public class CommunityResponseDTO {

    @Schema(description = "지난달 인기 게시글")
    private PostMonthResponseDTO postMonth;

    @Schema(description = "검색 게시글 목록")
    private SearchPostResponseDTO post;

    @Schema(description = "실시간 인기 게시글 목록")
    private List<PostPopularListResponseDTO> popularPosts;

    @Schema(description = "맞춤 게시글 목록")
    private List<PostAiListResponseDTO> postAiList;
}
