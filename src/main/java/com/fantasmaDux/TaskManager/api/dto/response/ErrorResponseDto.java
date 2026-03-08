package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String message;
    private int status;
    private String path;
    private String timestamp;
}
