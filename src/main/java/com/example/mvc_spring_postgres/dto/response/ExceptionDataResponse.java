package com.example.mvc_spring_postgres.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class ExceptionDataResponse {
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private int status;
    private String error;
    private String message;

    public ExceptionDataResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
