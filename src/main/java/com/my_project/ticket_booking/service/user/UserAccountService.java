package com.my_project.ticket_booking.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService {
    ResponseEntity<?> getUserInfo(String accessToken);
}
