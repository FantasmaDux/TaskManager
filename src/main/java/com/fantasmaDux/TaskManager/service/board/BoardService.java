package com.fantasmaDux.TaskManager.service.board;

import com.fantasmaDux.TaskManager.api.dto.request.BoardCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.BoardUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.api.dto.response.TaskResponseDto;

import java.util.UUID;

public interface BoardService {

    TaskResponseDto moveTask(UUID taskId, UUID boardId);

    BoardResponseDto createBoard(BoardCreationRequestDto board);

    BoardResponseDto getBoardById(UUID boardId);

    BoardResponseDto updateBoardById(UUID boardId, BoardUpdateRequestDto newBoard);

    void deleteBoardById(UUID boardId);
}
