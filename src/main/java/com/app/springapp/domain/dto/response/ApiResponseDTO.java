package com.app.springapp.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

// 최종 프로젝트 사용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ApiResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static <T>ApiResponseDTO<T> of(boolean success, String message){
        return new ApiResponseDTO<>(success, message);
    }

    public static <T>ApiResponseDTO<T> of(boolean success, String message, T data){
        return new ApiResponseDTO<>(success, message, data);
    }

}
