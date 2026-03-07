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
@Table(name = "attachments")
public class AttachmentEntity {

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
    private String fileName;
    @Column(nullable = false)
    private String storagePath;
    private Long size;
    private String contentType;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskEntity task;

    private Instant createdAt;
    private Instant updatedAt;
}
