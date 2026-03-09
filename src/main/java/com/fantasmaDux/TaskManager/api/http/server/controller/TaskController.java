package com.fantasmaDux.TaskManager.api.http.server.controller;

import com.fantasmaDux.TaskManager.api.dto.request.TaskCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.TaskUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import com.fantasmaDux.TaskManager.api.dto.response.TaskResponseDto;
import com.fantasmaDux.TaskManager.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardApiResponse<TaskResponseDto>> getTaskById(@PathVariable UUID id) {
        TaskResponseDto task = taskService.getTaskById(id);

        return ResponseEntity.ok().body(new StandardApiResponse<>("Task found", task));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<StandardApiResponse<List<TaskResponseDto>>>
    getTasksByBoard(@PathVariable UUID boardId) {
        List<TaskResponseDto> tasks = taskService.getTasksByBoard(boardId);

        return ResponseEntity.ok().body(new StandardApiResponse<>("Tasks found", tasks));
    }

    @PostMapping
    public ResponseEntity<StandardApiResponse<TaskResponseDto>>
    createTask(@RequestBody TaskCreationRequestDto request) {
        TaskResponseDto task = taskService.createTask(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardApiResponse<>("Task created", task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardApiResponse<TaskResponseDto>>
    updateTask(@PathVariable UUID id, @RequestBody TaskUpdateRequestDto request) {
        TaskResponseDto task = taskService.updateTaskById(id, request);
        return ResponseEntity.ok().body(new StandardApiResponse<>("Task updated", task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardApiResponse<Object>>
    deleteTask(@PathVariable UUID id) {
        taskService.deleteTaskById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
