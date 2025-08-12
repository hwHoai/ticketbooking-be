package com.example.mvc_spring_postgres.dto.response;

import lombok.Data;

@Data
public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String exp;

    public JwtTokenResponse(String accessToken, String refreshToken, String exp) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.exp = exp;
    }
}
