package com.example.mvc_spring_postgres.service.user.impl;

import com.example.mvc_spring_postgres.service.user.JwtTokenService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Value("${spring.security.oauth2.client.registration.auth0.redirect-uri}")
    private String redirectUri;

    @Override
    public ResponseEntity<?> oauthGetAccessToken(String code) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()   //mediaType: application/x-www-form-urlencoded
                    .add("grant_type", "authorization_code")
                    .add("code", code)
                    .add("redirect_uri", redirectUri)
                    .build();
            String credentials = java.util.Base64.getEncoder()
                    .encodeToString((clientId + ":" + clientSecret).getBytes());
            String authHeader = "Basic " + credentials;

            Request request = new Request.Builder()
                    .url(issuerUri + "oauth/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", authHeader)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
                if(!response.isSuccessful()){
                    return ResponseEntity.status(HttpStatus.valueOf(response.code())).body(response.body().string());
                }
                return ResponseEntity.ok(response.body().string());
            }

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> oauthRefreshToken(String refreshToken) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()   //mediaType: application/x-www-form-urlencoded
                    .add("grant_type", "refresh_token")
                    .add("client_id", clientId)
                    .add("client_secret", clientSecret)
                    .add("refresh_token", refreshToken)
                    .build();

            Request request = new Request.Builder()
                    .url(issuerUri + "oauth/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
                if (!response.isSuccessful()) {
                    return ResponseEntity.status(HttpStatus.valueOf(response.code())).body(response.body().string());
                }
                return ResponseEntity.ok(response.body().string());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
