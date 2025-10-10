package com.my_project.ticket_booking.dto.request;

import lombok.Data;

@Data
public class RefreshTokenReqBody {
    private String refreshToken;

    public RefreshTokenReqBody(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
