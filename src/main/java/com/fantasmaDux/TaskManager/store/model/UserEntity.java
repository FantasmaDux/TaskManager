package com.fantasmaDux.TaskManager.store.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
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
    private UUID keycloakId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<BoardEntity> boards;

    @ManyToMany(mappedBy = "assignees")
    private List<TaskEntity> tasks;

    private Instant createdAt;
    private Instant updatedAt;
}
