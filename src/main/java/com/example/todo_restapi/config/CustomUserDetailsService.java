package com.example.todo_restapi.config;

import com.example.todo_restapi.exception.UserNotExistException;
import com.example.todo_restapi.models.UserDto;
import com.example.todo_restapi.service.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceImplementation userServiceImplementation;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userServiceImplementation.getUserDtoByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UserNotExistException("Użytkownik o loginie %s nie istnieje".formatted(email)));
    }

    private UserDetails createUserDetails(UserDto userDto) {
        return User.builder()
                .username(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
