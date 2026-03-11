package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.request.UserRegistrationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public interface UserService {
    UserResponseDto getUserById(UUID userId);

    UserResponseDto registerUser(UserRegistrationRequestDto request);

    UserResponseDto findOrCreateUser(String email, Jwt jwt);
}
