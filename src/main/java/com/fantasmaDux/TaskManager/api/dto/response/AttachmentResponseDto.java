package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AttachmentResponseDto {
    private UUID id;
    private String fileName;
    private String storagePath;
    private Long size;
    private String contentType;
}
