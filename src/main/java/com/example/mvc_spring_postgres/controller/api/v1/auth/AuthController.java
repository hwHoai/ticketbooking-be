package com.example.mvc_spring_postgres.controller.api.v1.auth;

import com.example.mvc_spring_postgres.service.user.impl.JwtTokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private JwtTokenServiceImpl jwtTokenService;

    //**************GET**************//

    @GetMapping("access_token/{code}")
    public ResponseEntity<?> getAccessToken(@PathVariable String code, @RequestParam(name = "redirect_uri") String redirectUri) {
        return jwtTokenService.oauthGetAccessToken(code, redirectUri);
    }
    //**************POST**************//



    //**************PUT**************//



    //**************PATCH**************//


    //**************DELETE**************//

}
