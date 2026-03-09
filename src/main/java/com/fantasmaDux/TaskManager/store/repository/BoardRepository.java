package com.fantasmaDux.TaskManager.store.repository;

import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {
    List<BoardEntity> findAllByAuthor(UserEntity userEntity);
}
