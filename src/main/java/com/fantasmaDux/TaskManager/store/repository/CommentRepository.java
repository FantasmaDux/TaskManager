package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
