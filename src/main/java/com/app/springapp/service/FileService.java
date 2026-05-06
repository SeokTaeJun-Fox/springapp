package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.util.List;

public interface FileService {

    // 파일 1개 업로드
    public ApiResponseDTO uploadFile(MultipartFile uploadFile);

    // 파일 여러개 업로드
    public ApiResponseDTO uploadFiles(List<MultipartFile> uploadFiles);

    // 절대 경로를 숨기는 display
    public ResponseBytes<GetObjectResponse> getDisplayPath(String fileName);
}
