package com.app.springapp.controller;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import com.app.springapp.service.GuestbookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "방명록 API", description = "방명록 목록 조회/작성/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbook")
public class GuestbookController {

    private final GuestbookService guestbookService;

    @Operation(summary = "방명록 목록 조회", description = "ownerMemberId에 해당하는 방명록 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO> findAllByOwnerMemberId(@RequestParam Long ownerMemberId) {
        GuestbookResponseDTO dto = new GuestbookResponseDTO();
        dto.setOwnerMemberId(ownerMemberId);
        return ResponseEntity.ok(ApiResponseDTO.of(
                true, "방명록 목록 조회 성공",
                guestbookService.findAllByOwnerMemberId(dto)));
    }

    @Operation(summary = "방명록 작성", description = "방명록을 작성합니다.")
    @PostMapping("/write")
    public ResponseEntity<ApiResponseDTO> createGuestbook(@RequestBody GuestbookCreateRequestDTO guestbookCreateRequestDTO) {
        guestbookService.createGuestbook(guestbookCreateRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 작성 성공"));
    }

    @Operation(summary = "방명록 수정", description = "방명록 내용을 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO> updateGuestbook(@RequestBody GuestbookVO guestbookVO) {
        guestbookService.updateGuestbook(guestbookVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 수정 성공"));
    }

    @Operation(summary = "방명록 삭제", description = "방명록을 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO> deleteGuestbook(@RequestBody GuestbookVO guestbookVO) {
        guestbookService.deleteGuestbook(guestbookVO);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "방명록 삭제 성공"));
    }
}
