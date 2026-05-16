package com.example.securelogin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    @Test
    void testLogin() {
        LoginController controller = new LoginController();
        assertEquals("Login Successful", controller.login());
    }

    @Test
    void testHome() {
        LoginController controller = new LoginController();
        assertEquals("Secure Login Application is Running", controller.home());
    }
}