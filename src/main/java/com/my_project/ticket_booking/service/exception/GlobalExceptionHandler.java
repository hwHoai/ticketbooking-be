package com.my_project.ticket_booking.service.exception;

import com.my_project.ticket_booking.dto.response.ExceptionDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDataResponse> customException(CustomException e) {
        return new ResponseEntity<ExceptionDataResponse>(new ExceptionDataResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorize", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
