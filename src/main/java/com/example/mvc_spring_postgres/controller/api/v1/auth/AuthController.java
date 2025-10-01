package com.example.mvc_spring_postgres.controller.api.v1.auth;

import com.example.mvc_spring_postgres.dto.request.RefreshTokenReqBody;
import com.example.mvc_spring_postgres.service.user.impl.JwtTokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@Controller
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private JwtTokenServiceImpl jwtTokenService;

    //**************GET**************//

    @GetMapping("access_token/{code}")
    public ResponseEntity<?> getAccessToken(@PathVariable String code) {
        return jwtTokenService.oauthGetAccessToken(code);
    }
    //**************POST**************//
    @PostMapping("refresh_token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenReqBody body) {
        String refreshToken = body.getRefreshToken();
        return jwtTokenService.oauthRefreshToken(refreshToken);
    }


    //**************PUT**************//



    //**************PATCH**************//


    //**************DELETE**************//

}
