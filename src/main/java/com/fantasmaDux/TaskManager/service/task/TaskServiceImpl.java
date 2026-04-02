package com.fantasmaDux.TaskManager.service.task;

import com.fantasmaDux.TaskManager.api.dto.request.TaskCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.TaskUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.TaskResponseDto;
import com.fantasmaDux.TaskManager.api.exception.BoardNotFoundException;
import com.fantasmaDux.TaskManager.api.exception.TaskNotFoundException;
import com.fantasmaDux.TaskManager.mapper.TaskMapper;
import com.fantasmaDux.TaskManager.store.model.AttachmentEntity;
import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import com.fantasmaDux.TaskManager.store.model.TaskEntity;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import com.fantasmaDux.TaskManager.store.repository.AttachmentRepository;
import com.fantasmaDux.TaskManager.store.repository.BoardRepository;
import com.fantasmaDux.TaskManager.store.repository.TaskRepository;
import com.fantasmaDux.TaskManager.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final BoardRepository boardRepository;

    @Override
    public TaskResponseDto createTask(TaskCreationRequestDto request) {
        TaskEntity task = new TaskEntity();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        List<UserEntity> assignees = userRepository.findAllById(request.getAssigneeIds());
        task.setAssignees(assignees);
        List<AttachmentEntity> attachments = attachmentRepository.findAllById(request.getAttachmentIds());
        task.setAttachments(attachments);

        BoardEntity board = boardRepository.findById(request.getBoardId())
                .orElseThrow(BoardNotFoundException::new);
        task.setBoard(board);

        int position = taskRepository.findLastPosition(board.getId()) + 1;
        task.setPosition(position);

        TaskEntity savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(
                TaskNotFoundException::new
        );
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDto> getTasksByBoard(UUID boardId) {
        List<TaskEntity> tasks = taskRepository.findAllByBoardId(boardId);
        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponseDto updateTaskById(UUID taskId, TaskUpdateRequestDto newTaskRequest) {

        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);

        if (newTaskRequest.getTitle() != null) {
            task.setTitle(newTaskRequest.getTitle());
        }

        if (newTaskRequest.getDescription() != null) {
            task.setDescription(newTaskRequest.getDescription());
        }

        if (newTaskRequest.getStatus() != null) {
            task.setStatus(newTaskRequest.getStatus());
        }

        if (newTaskRequest.getPriority() != null) {
            task.setPriority(newTaskRequest.getPriority());
        }

        if (newTaskRequest.getDueDate() != null) {
            task.setDueDate(newTaskRequest.getDueDate());
        }

        if (newTaskRequest.getAssigneeIds() != null) {
            List<UserEntity> assignees = userRepository.findAllById(newTaskRequest.getAssigneeIds());
            task.setAssignees(assignees);
        }

        if (newTaskRequest.getAttachmentIds() != null) {
            List<AttachmentEntity> attachments =
                    attachmentRepository.findAllById(newTaskRequest.getAttachmentIds());
            task.setAttachments(attachments);
        }

        if (newTaskRequest.getPosition() != null) {
            task.setPosition(newTaskRequest.getPosition());
        }

        return taskMapper.toDto(task);
    }

    @Override
    public void deleteTaskById(UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(
                TaskNotFoundException::new
        );
        taskRepository.delete(task);
    }
}