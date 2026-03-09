package com.fantasmaDux.TaskManager.mapper;

import com.fantasmaDux.TaskManager.api.dto.request.BoardCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.BoardUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardResponseDto toDto(BoardEntity boardEntity);

    BoardEntity createEntityFromDto(BoardCreationRequestDto dto);

    void updateEntityFromDto(BoardUpdateRequestDto dto, @MappingTarget BoardEntity boardEntity);
}
