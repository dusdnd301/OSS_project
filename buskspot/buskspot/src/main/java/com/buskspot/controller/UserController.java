package com.buskspot.controller;

import com.buskspot.dto.UserDTO;
import com.buskspot.entity.User;
import com.buskspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .nickname(userDTO.getNickname())
                .build();
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
    }
}
