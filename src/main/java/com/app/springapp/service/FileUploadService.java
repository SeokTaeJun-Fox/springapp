package com.app.springapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {

    private String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (created) {
                    log.info("Upload directory created at: {}", uploadDir);
                }
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            
            String savedFilename = UUID.randomUUID().toString() + extension;
            File dest = new File(uploadDir + savedFilename);
            file.transferTo(dest);
            
            // 프론트엔드에서 접근할 수 있는 URL 반환
            return "http://localhost:10000/uploads/" + savedFilename;
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
}
