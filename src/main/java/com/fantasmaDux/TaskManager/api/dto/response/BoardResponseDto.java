package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class BoardResponseDto {
    private UUID id;
    private String title;
    private String description;
    private UUID authorId;
    private List<UUID> taskIds;
    private Instant createdAt;
    private Instant updatedAt;
}
