package com.fantasmaDux.TaskManager.mapper;

import com.fantasmaDux.TaskManager.api.dto.response.TaskResponseDto;
import com.fantasmaDux.TaskManager.store.model.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toDto(TaskEntity task);
}
