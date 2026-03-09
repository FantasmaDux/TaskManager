package com.fantasmaDux.TaskManager.mapper;

import com.fantasmaDux.TaskManager.api.dto.response.CommentResponseDto;
import com.fantasmaDux.TaskManager.store.model.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponseDto toDto(CommentEntity comment);
}
