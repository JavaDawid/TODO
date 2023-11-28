package com.example.todo_restapi.controller;

import com.example.todo_restapi.models.UserRegistrationDto;
import com.example.todo_restapi.service.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MvcRegistrationController {
    private final UserServiceImplementation userServiceImplementation;

    @GetMapping("/register")
    public String registrationUserForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDto userRegistrationDto) {
        userServiceImplementation.registerUser(userRegistrationDto);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String registrationConfirmation() {
        return "registration-confirmation";
    }
}
