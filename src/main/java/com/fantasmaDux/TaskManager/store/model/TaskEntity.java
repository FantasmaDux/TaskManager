package com.fantasmaDux.TaskManager.store.model;

import com.fantasmaDux.TaskManager.store.enums.PriorityEnum;
import com.fantasmaDux.TaskManager.store.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.github.f4b6a3.uuid.UuidCreator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "tasks")
public class TaskEntity {

    @Id
    private UUID id;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UuidCreator.getTimeOrderedEpoch(); // UUIDv7
            createdAt = Instant.now();
        }
    }

    @Column(nullable = false)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;
    private Instant dueDate;
    private Integer position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityEnum priority;

    @ManyToMany
    @JoinTable(
            name = "task_assignee",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> assignees;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachmentEntity> attachments;

    private Instant createdAt;
    private Instant updatedAt;
}
