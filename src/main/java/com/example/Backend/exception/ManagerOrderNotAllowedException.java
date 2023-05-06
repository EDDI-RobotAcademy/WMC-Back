package com.example.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ManagerOrderNotAllowedException extends RuntimeException {
    public ManagerOrderNotAllowedException(String message) {
        super(message);
    }
}
