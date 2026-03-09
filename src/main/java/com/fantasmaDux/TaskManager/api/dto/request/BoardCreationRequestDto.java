package com.fantasmaDux.TaskManager.api.dto.request;

import lombok.Data;

@Data
public class BoardCreationRequestDto {
    private String title;
    private String description;
}
