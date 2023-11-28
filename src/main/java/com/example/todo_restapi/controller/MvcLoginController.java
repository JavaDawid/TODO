package com.example.todo_restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcLoginController {
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
