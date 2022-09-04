package com.example.jazs23638nbp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class Advice {
    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> HandlerRuntimeException(HttpClientErrorException exception) {
        return switch (exception.getStatusCode()) {
            case INTERNAL_SERVER_ERROR -> ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Błąd 500");
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Błąd 404");
            case BAD_REQUEST -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd 400");
            case NO_CONTENT -> ResponseEntity.status(HttpStatus.NO_CONTENT).body("Błąd 204");
            default -> ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Błąd: 502");
        };
    }
}