package com.example.mvc_spring_postgres.dto.request;

import lombok.Data;

@Data
public class CreateUserBodyRequest {
    String userName;
    String password;
    String email;
    void callBack () {;
        // This method can be used for any additional processing or validation
        // after the object is created or before it is used.
    }

    public CreateUserBodyRequest(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
