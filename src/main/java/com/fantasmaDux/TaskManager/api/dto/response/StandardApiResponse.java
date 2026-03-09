package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

// class for data returning to front
@Data
@AllArgsConstructor
public class StandardApiResponse<T> {
    private String message;
    private T data;
}
