package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
