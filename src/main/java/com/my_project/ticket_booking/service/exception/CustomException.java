package com.my_project.ticket_booking.service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Getter
@Setter
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message, null, false, true);
    }
}
