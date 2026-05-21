package com.app.springapp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class PostLikeException extends RuntimeException {

    private HttpStatus httpStatus;

    public PostLikeException(String message) {
        super(message);
    }
    public PostLikeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
