package com.example.mvc_spring_postgres.service.user;

import com.example.mvc_spring_postgres.dto.response.UserInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserAccountService {
    ResponseEntity<?> getUserInfo(String accessToken);
}
