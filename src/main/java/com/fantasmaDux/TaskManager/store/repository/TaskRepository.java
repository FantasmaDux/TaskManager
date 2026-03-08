package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByBoardId(UUID boardId);

    @Query("SELECT COALESCE(MAX(t.position), 0) FROM TaskEntity t WHERE t.board.id = :id")
    int findLastPosition(UUID id);
}
