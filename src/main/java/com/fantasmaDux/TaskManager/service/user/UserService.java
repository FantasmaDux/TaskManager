package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;

import java.util.UUID;

public interface UserService {
    UserResponseDto getUserById(UUID userId);

}
