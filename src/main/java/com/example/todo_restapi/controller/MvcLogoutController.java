package com.example.todo_restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MvcLogoutController {
    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }
}
