package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.api.exception.UserNotFoundException;
import com.fantasmaDux.TaskManager.mapper.BoardMapper;
import com.fantasmaDux.TaskManager.mapper.UserMapper;
import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import com.fantasmaDux.TaskManager.store.repository.BoardRepository;
import com.fantasmaDux.TaskManager.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity userEntity
                = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return userMapper.userToUserResponseDto(userEntity);
    }

    @Override
    public List<BoardResponseDto> getUserBoards(UUID userId) {
        UserEntity userEntity
                = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<BoardEntity> boardEntityList = boardRepository.findAllByAuthor(userEntity);
        return boardEntityList.stream()
                .map(boardMapper::toDto)
                .collect(Collectors.toList());
    }
}
