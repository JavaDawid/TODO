package com.example.todo_restapi.mapper;

import com.example.todo_restapi.models.User;
import com.example.todo_restapi.models.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public class UserMapper {
    public UserDto userMapToUserCredentialsDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCreateDate());
    }
}
