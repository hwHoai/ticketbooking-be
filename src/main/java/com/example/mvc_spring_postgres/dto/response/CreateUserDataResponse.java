package com.example.mvc_spring_postgres.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDataResponse {
    UUID userId;
    String accessToken;
    String refreshToken;

    public CreateUserDataResponse(UUID userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
