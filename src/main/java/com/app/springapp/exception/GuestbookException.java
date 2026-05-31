package com.app.springapp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class GuestbookException extends RuntimeException {

    private HttpStatus httpStatus;

    public GuestbookException(String message) {
        super(message);
    }
    public GuestbookException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
