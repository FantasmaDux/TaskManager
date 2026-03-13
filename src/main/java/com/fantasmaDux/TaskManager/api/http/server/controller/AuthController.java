package com.fantasmaDux.TaskManager.api.http.server.controller;

import com.fantasmaDux.TaskManager.api.dto.request.UserRegistrationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

    // method for testing
    @GetMapping("/me")
    public UserResponseDto getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaim("email");
        return userService.findOrCreateUser(email, jwt);
    }

}
