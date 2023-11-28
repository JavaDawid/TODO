package com.example.todo_restapi.controller;

import com.example.todo_restapi.service.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MvcChangePasswordController {
    private final UserServiceImplementation userServiceImplementation;

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword) {
        userServiceImplementation.changeCurrentPassword(newPassword);
        return "redirect:/logout";
    }
}
