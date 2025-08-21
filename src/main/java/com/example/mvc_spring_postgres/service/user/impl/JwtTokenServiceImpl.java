package com.example.mvc_spring_postgres.service.user.impl;

import com.example.mvc_spring_postgres.service.user.JwtTokenService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Override
    public String oauthGetAccessToken(String code, String redirectUri) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&" +
                    "client_id="+clientId+"&" +
                    "client_secret="+clientSecret+"&" +
                    "code="+code+"&" +
                    "redirect_uri="+redirectUri);
            Request request = new Request.Builder()
                    .url("https://huuhoai.jp.auth0.com/oauth/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get access token: " + e.getMessage(), e);
        }

    }
}
