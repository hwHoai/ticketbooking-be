package com.example.mvc_spring_postgres.controller.api.v1.auth;

import com.example.mvc_spring_postgres.dto.request.CreateUserBodyRequest;
import com.example.mvc_spring_postgres.dto.response.CreateUserDataResponse;
import com.example.mvc_spring_postgres.service.user.impl.JwtTokenServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
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


    @GetMapping("access_token")
    public ResponseEntity<String> getAccessToken()throws IOException {
        try {
            assert jwtTokenService != null;
            String jwt = jwtTokenService.oauthGetAccessToken();
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
        }
        catch (Exception e) {
            log.error("Error getting access token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    //**************POST**************//



    //**************PUT**************//



    //**************PATCH**************//


    //**************DELETE**************//

}
