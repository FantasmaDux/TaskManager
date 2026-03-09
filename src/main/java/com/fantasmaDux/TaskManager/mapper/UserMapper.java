package com.fantasmaDux.TaskManager.mapper;

import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto userToUserResponseDto(UserEntity user);
}
