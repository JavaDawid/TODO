package com.example.todo_restapi.controller;

import com.example.todo_restapi.models.UserDto;
import com.example.todo_restapi.models.UserRegistrationDto;
import com.example.todo_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ApiUserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        Long userId = userService.registerUser(userRegistrationDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
