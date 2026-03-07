package com.fantasmaDux.TaskManager.store.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    private UUID id;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UuidCreator.getTimeOrderedEpoch(); // UUIDv7
            createdAt = Instant.now();
        }
    }

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskEntity task;

    private Instant createdAt;
    private Instant updatedAt;
}
