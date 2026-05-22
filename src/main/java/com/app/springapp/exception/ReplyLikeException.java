package com.app.springapp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ReplyLikeException extends RuntimeException {

    private HttpStatus httpStatus;

    public ReplyLikeException(String message) {
        super(message);
    }
    public ReplyLikeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
