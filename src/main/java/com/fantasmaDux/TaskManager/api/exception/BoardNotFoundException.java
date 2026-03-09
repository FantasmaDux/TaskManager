package com.fantasmaDux.TaskManager.api.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(String message) {super(message);}
    public BoardNotFoundException() {super("Board not found");}
}
