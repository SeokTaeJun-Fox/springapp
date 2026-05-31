package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.GuestbookRereplyCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.vo.GuestbookRereplyVO;
import com.app.springapp.service.GuestbookRereplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "방명록 대댓글 API", description = "방명록 대댓글 목록 조회/작성/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbook/rereply")
public class GuestbookRereplyController {

    private final GuestbookRereplyService guestbookRereplyService;

    @Operation(summary = "방명록 대댓글 목록 조회", description = "guestbookReplyId에 해당하는 대댓글 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO> findAllByGuestbookReplyId(@RequestParam Long guestbookReplyId) {
        return ResponseEntity.ok(ApiResponseDTO.of(
                true, "방명록 대댓글 목록 조회 성공",
                guestbookRereplyService.findAllByGuestbookReplyId(guestbookReplyId)));
    }

    @Operation(summary = "방명록 대댓글 작성", description = "방명록 대댓글을 작성합니다.")
    @PostMapping("/write")
    public ResponseEntity<ApiResponseDTO> createRereply(@RequestBody GuestbookRereplyCreateRequestDTO guestbookRereplyCreateRequestDTO) {
        guestbookRereplyService.createRereply(guestbookRereplyCreateRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 대댓글 작성 성공"));
    }

    @Operation(summary = "방명록 대댓글 수정", description = "방명록 대댓글 내용을 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO> updateRereply(@RequestBody GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyService.updateRereply(guestbookRereplyVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 대댓글 수정 성공"));
    }

    @Operation(summary = "방명록 대댓글 삭제", description = "방명록 대댓글을 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO> deleteRereply(@RequestBody GuestbookRereplyVO guestbookRereplyVO) {
        guestbookRereplyService.deleteRereply(guestbookRereplyVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 대댓글 삭제 성공"));
    }
}
