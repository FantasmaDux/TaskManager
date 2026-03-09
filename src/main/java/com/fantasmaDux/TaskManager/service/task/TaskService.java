package com.fantasmaDux.TaskManager.service.task;

import com.fantasmaDux.TaskManager.api.dto.request.TaskCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.TaskUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponseDto createTask(TaskCreationRequestDto task);

    TaskResponseDto getTaskById(UUID taskId);

    List<TaskResponseDto> getTasksByBoard(UUID boardId);

    TaskResponseDto updateTaskById(UUID taskId, TaskUpdateRequestDto newTask);

    void deleteTaskById(UUID taskId);
}
