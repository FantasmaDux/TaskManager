package com.fantasmaDux.TaskManager.api.dto.response;

import com.fantasmaDux.TaskManager.store.enums.PriorityEnum;
import com.fantasmaDux.TaskManager.store.enums.StatusEnum;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class TaskResponseDto {
    private UUID id;
    private String title;
    private String description;
    private StatusEnum status;
    private Instant dueDate;
    private Integer position;
    private PriorityEnum priority;
    private List<UUID> assigneeIds;
    private List<CommentResponseDto> comments;
    private List<AttachmentResponseDto> attachments;
    private Instant createdAt;
    private Instant updatedAt;
}
