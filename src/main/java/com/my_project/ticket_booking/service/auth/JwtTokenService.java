package com.my_project.ticket_booking.service.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    ResponseEntity<?> oauthGetAccessToken(String code);
    ResponseEntity<?> oauthRefreshToken(String accessToken);

}