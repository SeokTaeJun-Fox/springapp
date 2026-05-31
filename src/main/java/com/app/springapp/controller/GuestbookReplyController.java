package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.GuestbookReplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.vo.GuestbookReplyVO;
import com.app.springapp.service.GuestbookReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "방명록 댓글 API", description = "방명록 댓글 목록 조회/작성/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbook/reply")
public class GuestbookReplyController {

    private final GuestbookReplyService guestbookReplyService;

    @Operation(summary = "방명록 댓글 목록 조회", description = "guestbookId에 해당하는 댓글 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO> findAllByGuestbookId(@RequestParam Long guestbookId) {
        return ResponseEntity.ok(ApiResponseDTO.of(
                true, "방명록 댓글 목록 조회 성공",
                guestbookReplyService.findAllByGuestbookId(guestbookId)));
    }

    @Operation(summary = "방명록 댓글 작성", description = "방명록 댓글을 작성합니다.")
    @PostMapping("/write")
    public ResponseEntity<ApiResponseDTO> createReply(@RequestBody GuestbookReplyCreateRequestDTO guestbookReplyCreateRequestDTO) {
        guestbookReplyService.createReply(guestbookReplyCreateRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 댓글 작성 성공"));
    }

    @Operation(summary = "방명록 댓글 수정", description = "방명록 댓글 내용을 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO> updateReply(@RequestBody GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyService.updateReply(guestbookReplyVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 댓글 수정 성공"));
    }

    @Operation(summary = "방명록 댓글 삭제", description = "방명록 댓글을 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO> deleteReply(@RequestBody GuestbookReplyVO guestbookReplyVO) {
        guestbookReplyService.deleteReply(guestbookReplyVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 댓글 삭제 성공"));
    }
}
