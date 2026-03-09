package com.fantasmaDux.TaskManager.api.exception;

import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleBoardNotFoundException(BoardNotFoundException e) {
        log.warn("Board not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardApiResponse<>(e.getMessage(), null));

    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleTaskNotFoundException(BoardNotFoundException e) {
        log.warn("Task not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardApiResponse<>(e.getMessage(), null));

    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<StandardApiResponse<Object>> handleUserNotFoundException(BoardNotFoundException e) {
        log.warn("User not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardApiResponse<>(e.getMessage(), null));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardApiResponse<Object>> handleGenericException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new StandardApiResponse<>(e.getMessage(), null)
        );
    }
}
