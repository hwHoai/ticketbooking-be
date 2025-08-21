package com.example.mvc_spring_postgres.controller.api.v1.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController()
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {

    //**************GET**************//
    @GetMapping("/1")
    public ResponseEntity<String> getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication());
        return ResponseEntity.ok("Hello User");
    }


    //**************POST**************//



    //**************PUT**************//



    //**************PATCH**************//



    //**************DELETE**************//
}
