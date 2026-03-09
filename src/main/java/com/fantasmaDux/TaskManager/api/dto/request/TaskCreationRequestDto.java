package com.fantasmaDux.TaskManager.api.dto.request;

import com.fantasmaDux.TaskManager.store.enums.PriorityEnum;
import com.fantasmaDux.TaskManager.store.enums.StatusEnum;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class TaskCreationRequestDto {
    private String title;
    private String description;
    private StatusEnum status;
    private Instant dueDate;
    private PriorityEnum priority;
    private UUID boardId;
    private List<UUID> assigneeIds;
    private List<UUID> attachmentIds;
}
