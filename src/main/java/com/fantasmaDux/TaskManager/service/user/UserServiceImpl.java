package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.api.exception.UserNotFoundException;
import com.fantasmaDux.TaskManager.mapper.UserMapper;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import com.fantasmaDux.TaskManager.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity userEntity
                = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return userMapper.userToUserResponseDto(userEntity);
    }

}
