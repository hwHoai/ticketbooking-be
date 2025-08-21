package com.example.mvc_spring_postgres.service.user;

import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    String oauthGetAccessToken(String code, String redirectUri);

}