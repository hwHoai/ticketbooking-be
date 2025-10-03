package com.example.mvc_spring_postgres.dto.request;

import lombok.Data;

@Data
public class RefreshTokenReqBody {
    private String refreshToken;

    public RefreshTokenReqBody(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
