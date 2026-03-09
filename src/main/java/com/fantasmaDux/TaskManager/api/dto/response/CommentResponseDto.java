package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CommentResponseDto {
    private UUID id;
    private String text;
    private UUID authorId;
    private Instant createdAt;
}
