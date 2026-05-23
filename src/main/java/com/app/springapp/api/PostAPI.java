package com.app.springapp.api;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.PostLikeService;
import com.app.springapp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Slf4j
@Tag(name = "POST API", description = "게시글 API")
public class PostAPI {

    private final PostService postService;
    private final PostLikeService postLikeService;

    //게시글 열람
    @PostMapping("/read")
    @Operation(summary = "게시글 열람 서비스", description = "게시글을 열람하는 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 작성 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> readPost(@RequestBody PostReadRequestDTO postReadRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 읽기 성공",
                        postService.getPostDetailInfo(postReadRequestDTO)));
    }

    //게시글 좋아요 등록
    @PostMapping("/apply-like")
    @Operation(summary = "게시글 좋아요 서비스", description = "게시글 좋아요 등록 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 좋아요 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> applyLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {

        log.info("{}", postLikeRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 좋아요 등록 성공",
                        postLikeService.likePost(postLikeRequestDTO)));
    }

    //게시글 좋아요 취소
    @PostMapping("/cancel-like")
    @Operation(summary = "게시글 좋아요 취소 서비스", description = "게시글 좋아요 취소 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 좋아요 취소 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> cancelLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 좋아요 취소 성공",
                        postLikeService.cancelPostLike(postLikeRequestDTO)));
    }

}
