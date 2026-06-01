package com.app.springapp.api;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import com.app.springapp.domain.dto.request.ReplyReportCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyReportCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.PostReportService;
import com.app.springapp.service.ReplyReportService;
import com.app.springapp.service.RereplyReportService;
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
@RequestMapping("/api/CommunityReport")
@Slf4j
@Tag(name = "Community Report API", description = "커뮤니티 신고 API")
public class CommunityReportAPI {

    private final PostReportService postReportService;
    private final ReplyReportService replyReportService;
    private final RereplyReportService rereplyReportService;

    //게시글 신고
    @PostMapping("/post")
    @Operation(summary = "게시글 신고 서비스", description = "게시글을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> postReport(@RequestBody PostReportCreateRequestDTO postReportCreateRequestDTO) {

        postReportService.write(postReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 신고 성공"
                        ));
    }

    //댓글 신고
    @PostMapping("/reply")
    @Operation(summary = "댓글 신고 서비스", description = "댓글을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "댓글 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> replyReport(@RequestBody ReplyReportCreateRequestDTO replyReportCreateRequestDTO) {

        replyReportService.write(replyReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 신고 성공"
                ));
    }

    //대댓글 신고
    @PostMapping("/rereply")
    @Operation(summary = "대댓글 신고 서비스", description = "대댓글을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "대댓글 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> rereplyReport(@RequestBody RereplyReportCreateRequestDTO rereplyReportCreateRequestDTO) {

        rereplyReportService.write(rereplyReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "대댓글 신고 성공"
                ));
    }
}
