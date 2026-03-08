package com.fantasmaDux.TaskManager.api.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException() {
        super("Task not found");
    }
}
