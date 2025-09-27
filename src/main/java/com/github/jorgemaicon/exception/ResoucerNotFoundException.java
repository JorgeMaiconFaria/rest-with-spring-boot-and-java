package com.github.jorgemaicon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResoucerNotFoundException extends RuntimeException {
    public ResoucerNotFoundException(String message) {
        super(message);
    }
}
