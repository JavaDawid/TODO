package com.example.todo_restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcHomeController {

    @RequestMapping("")
    public String home() {
        return "home";
    }

}
