package com.fantasmaDux.TaskManager.api.dto.request;

import com.fantasmaDux.TaskManager.store.enums.PriorityEnum;
import com.fantasmaDux.TaskManager.store.enums.StatusEnum;
import com.fantasmaDux.TaskManager.store.model.AttachmentEntity;
import com.fantasmaDux.TaskManager.store.model.CommentEntity;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class TaskUpdateRequestDto {
    private String title;
    private String description;
    private StatusEnum status;
    private Instant dueDate;
    private Integer position;
    private PriorityEnum priority;
    private List<UUID> assigneeIds;
    private List<UUID> attachmentIds;
}
