package com.app.springapp.controller;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "File Upload API", description = "파일 업로드 API")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "파일 업로드", description = "파일을 서버 로컬 디스크에 저장하고 접근 가능한 URL을 반환합니다.")
    @PostMapping
    public ResponseEntity<ApiResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("File upload request received: {}", file.getOriginalFilename());
        String fileUrl = fileUploadService.saveFile(file);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "업로드 성공", fileUrl));
    }
}
