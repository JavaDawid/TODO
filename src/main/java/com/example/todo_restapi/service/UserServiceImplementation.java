package com.example.todo_restapi.service;

import com.example.todo_restapi.exception.UserExistException;
import com.example.todo_restapi.exception.UserNotExistException;
import com.example.todo_restapi.mapper.UserMapper;
import com.example.todo_restapi.models.User;
import com.example.todo_restapi.models.UserDto;
import com.example.todo_restapi.models.UserRegistrationDto;
import com.example.todo_restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long registerUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> userOptional = userRepository.findByEmail(userRegistrationDto.getEmail());
        if (userOptional.isPresent()) {
            throw new UserExistException("Użytkownik o takim email'u istinieje");
        } else {
            User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmail(),
                    hashPassword(userRegistrationDto.getPassword()),
                    Instant.now(Clock.systemDefaultZone()));
            User savedUser = userRepository.save(user);
            return savedUser.getId();
        }//dodać walidację do klasy TaskServiceImplementation saveTask()
    }

    @Override
    public Optional<UserDto> getUserDtoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userMapToUserCredentialsDto);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void changeCurrentPassword(String newPassword) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentUsername).orElseThrow(() -> new UserNotExistException("Użytkownik nie istnieje"));
        String hashPassword = hashPassword(newPassword);
        user.setPassword(hashPassword);
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
