package com.app.springapp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class PostException extends RuntimeException {

    private HttpStatus httpStatus;

    public PostException(String message) {
        super(message);
    }
    public PostException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
