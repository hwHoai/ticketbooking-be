package com.example.mvc_spring_postgres.controller.api.v1.user;

import com.example.mvc_spring_postgres.dto.response.UserInfoResponse;
import com.example.mvc_spring_postgres.service.user.UserAccountService;
import com.example.mvc_spring_postgres.service.user.impl.JwtTokenServiceImpl;
import com.example.mvc_spring_postgres.service.user.impl.UserAccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
