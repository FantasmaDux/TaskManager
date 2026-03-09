package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto getUserById(UUID userId);

    List<BoardResponseDto> getUserBoards(UUID userId);
}
