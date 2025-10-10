package com.my_project.ticket_booking.dto.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String name;
    private String email;
    private String picture;
    private String gender;
}
