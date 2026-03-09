package com.fantasmaDux.TaskManager.service.board;

import com.fantasmaDux.TaskManager.api.dto.request.BoardCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.BoardUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.api.exception.BoardNotFoundException;
import com.fantasmaDux.TaskManager.mapper.BoardMapper;
import com.fantasmaDux.TaskManager.store.model.BoardEntity;
import com.fantasmaDux.TaskManager.store.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto createBoard(BoardCreationRequestDto board) {
        BoardEntity boardEntity = boardMapper.createEntityFromDto(board);

        BoardEntity savedBoard = boardRepository.save(boardEntity);

        return boardMapper.toDto(savedBoard);
    }

    @Override
    public BoardResponseDto getBoardById(UUID boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        return boardMapper.toDto(boardEntity);
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoardById(UUID boardId, BoardUpdateRequestDto request) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        boardMapper.updateEntityFromDto(request, boardEntity);

        return boardMapper.toDto(boardEntity);
    }

    @Override
    public void deleteBoardById(UUID boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        boardRepository.delete(boardEntity);
    }
}
