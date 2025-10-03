package com.example.mvc_spring_postgres.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    ResponseEntity<?> oauthGetAccessToken(String code);
    ResponseEntity<?> oauthRefreshToken(String accessToken);

}