package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {
}
