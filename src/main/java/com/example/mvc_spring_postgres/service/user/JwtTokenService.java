package com.example.mvc_spring_postgres.service.user;

import com.example.mvc_spring_postgres.service.user.impl.JwtTokenServiceImpl;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    String oauthGetAccessToken();

}