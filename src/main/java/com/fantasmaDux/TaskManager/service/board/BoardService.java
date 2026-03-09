package com.fantasmaDux.TaskManager.service.board;

import com.fantasmaDux.TaskManager.api.dto.request.BoardCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.BoardUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;

import java.util.UUID;

public interface BoardService {

    BoardResponseDto createBoard(BoardCreationRequestDto board);

    BoardResponseDto getBoardById(UUID boardId);

    BoardResponseDto updateBoardById(UUID boardId, BoardUpdateRequestDto newBoard);

    void deleteBoardById(UUID boardId);
}
