package com.app.springapp.controller;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.exception.PostException;
import com.app.springapp.exception.PostLikeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PostLikeException.class)
    public ResponseEntity<ApiResponseDTO> handleException(PostLikeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(false, e.getMessage()));
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ApiResponseDTO> handleException(PostException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(false, e.getMessage()));
    }
}
