package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, UUID> {
}
