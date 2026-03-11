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
@Table(name = "boards")
public class BoardEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @OneToMany(mappedBy = "board", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

    private Instant createdAt;
    private Instant updatedAt;

}
