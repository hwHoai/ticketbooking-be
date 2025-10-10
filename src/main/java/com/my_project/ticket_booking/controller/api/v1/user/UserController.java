package com.my_project.ticket_booking.controller.api.v1.user;

import com.my_project.ticket_booking.service.user.impl.UserAccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RestController()
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserAccountServiceImpl userAccountService;

    //**************GET**************//
    @GetMapping("/info")
    public ResponseEntity<?> getUser(HttpServletRequest request) throws IOException {
        String accessToken = request.getHeader("Authorization");
        return userAccountService.getUserInfo(accessToken);
    }


    //**************POST**************//



    //**************PUT**************//



    //**************PATCH**************//



    //**************DELETE**************//
}
