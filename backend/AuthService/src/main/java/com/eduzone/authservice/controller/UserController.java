package com.eduzone.authservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public Object getProfile(Authentication authentication) {
        return new Object() {
            public final String message = "done";
            public final String user = authentication.getName();
        };
    }
}
