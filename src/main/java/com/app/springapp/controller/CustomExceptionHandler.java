package com.app.springapp.controller;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.exception.MemberException;
import com.app.springapp.exception.PostException;
import com.app.springapp.exception.PostLikeException;
import com.app.springapp.exception.ReplyLikeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponseDTO> handleMemberException(MemberException e) {
        HttpStatus status = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(ApiResponseDTO.of(false, e.getMessage()));
    }

    @ExceptionHandler(PostLikeException.class)
    public ResponseEntity<ApiResponseDTO> handleException(PostLikeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(false, e.getMessage()));
    }

    @ExceptionHandler(ReplyLikeException.class)
    public ResponseEntity<ApiResponseDTO> handleException(ReplyLikeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(false, e.getMessage()));
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ApiResponseDTO> handleException(PostException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(false, e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO> handleRuntimeException(RuntimeException e) {
        log.error("[RuntimeException] {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseDTO.of(false, e.getMessage()));
    }
}
