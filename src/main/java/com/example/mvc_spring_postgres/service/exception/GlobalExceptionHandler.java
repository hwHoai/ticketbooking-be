package com.example.mvc_spring_postgres.service.exception;

import com.example.mvc_spring_postgres.dto.response.ExceptionDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDataResponse> customException(CustomException e) {
        return new ResponseEntity<ExceptionDataResponse>(new ExceptionDataResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorize", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
