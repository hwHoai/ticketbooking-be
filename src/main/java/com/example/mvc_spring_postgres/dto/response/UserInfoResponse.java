package com.example.mvc_spring_postgres.dto.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String name;
    private String email;
    private String picture;
    private String gender;
}
