package com.example.todo_restapi.service;

import com.example.todo_restapi.models.User;
import com.example.todo_restapi.models.UserDto;
import com.example.todo_restapi.models.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Long registerUser(UserRegistrationDto userRegistrationDto);

    Optional<UserDto> getUserDtoByEmail(String email);

    Optional<User> getUserByEmail(String email);

    void changeCurrentPassword(String password);

}
