package com.fantasmaDux.TaskManager.api.http.server.controller;

import com.fantasmaDux.TaskManager.api.dto.request.UserRegistrationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<StandardApiResponse<UserResponseDto>>
    registerUser(@RequestBody UserRegistrationRequestDto request) {
        UserResponseDto response = userService.registerUser(request);
        return ResponseEntity.ok(new StandardApiResponse<>("User registered", response));
    }


}
