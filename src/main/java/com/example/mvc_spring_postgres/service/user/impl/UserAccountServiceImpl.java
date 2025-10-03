package com.example.mvc_spring_postgres.service.user.impl;

import com.example.mvc_spring_postgres.dto.response.UserInfoResponse;
import com.example.mvc_spring_postgres.service.user.UserAccountService;
import lombok.extern.flogger.Flogger;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    public ResponseEntity<?> getUserInfo(String accessToken) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url( issuerUri + "userinfo")
                    .method("GET", null)
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", accessToken)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
                if(!response.isSuccessful()){
                    return ResponseEntity.status(HttpStatus.valueOf(response.code())).body(response.body().string());
                }
                return ResponseEntity.ok().body(response.body().string());
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
