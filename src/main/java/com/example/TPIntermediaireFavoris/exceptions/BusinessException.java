package com.example.TPIntermediaireFavoris.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException{
    public BusinessException(String message) { super(message); }
}
