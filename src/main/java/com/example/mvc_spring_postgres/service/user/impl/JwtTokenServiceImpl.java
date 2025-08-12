package com.example.mvc_spring_postgres.service.user.impl;

import com.example.mvc_spring_postgres.service.user.JwtTokenService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Value("${spring.security.oauth2.resourceserver.jwt.audiences}")
    private String audiences;

    private JwtTokenServiceImpl () {
        // Default constructor
    }

    @Override
    public String oauthGetAccessToken() {
        try {
            System.out.println("Client ID: " + clientId);
            String body = "{\"client_id\":\""+clientId+"\",\"client_secret\":\""+clientSecret+"\",\"audience\":\""+audiences+"\",\"grant_type\":\"client_credentials\"}";
            System.out.println(body);
            HttpResponse<String> response = Unirest.post(issuerUri + "oauth/token")
                    .header("content-type", "application/json")
                    .body(body)
                    .asString();
            if (response.getStatus() != 200) {
                throw new RuntimeException(response.getStatusText());
            }
        return response.getBody();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get access token: " + e.getMessage(), e);
        }

    }
}
