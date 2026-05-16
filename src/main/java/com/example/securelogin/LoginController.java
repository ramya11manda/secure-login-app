package com.example.securelogin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "Secure Login Application is Running";
    }

    @GetMapping("/login")
    public String login() {
        return "Login Successful";
    }
}