package com.fantasmaDux.TaskManager.api.http.server.controller;

import com.fantasmaDux.TaskManager.api.dto.request.BoardCreationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.request.BoardUpdateRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.BoardResponseDto;
import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import com.fantasmaDux.TaskManager.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardApiResponse<BoardResponseDto>> getBoardById(@PathVariable UUID id) {
        BoardResponseDto board = boardService.getBoardById(id);

        return ResponseEntity.ok().body(new StandardApiResponse<>("Board found", board));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StandardApiResponse<List<BoardResponseDto>>>
    getBoardsByUserId(@PathVariable UUID userId) {
        List<BoardResponseDto> boards = boardService.getBoardsByUserId(userId);

        return ResponseEntity.ok().body(new StandardApiResponse<>("Boards found", boards));
    }

    @PostMapping()
    public ResponseEntity<StandardApiResponse<BoardResponseDto>>
    createBoard(@RequestBody BoardCreationRequestDto request) {
        BoardResponseDto board = boardService.createBoard(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardApiResponse<>("Board created", board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardApiResponse<BoardResponseDto>>
    updateBoard(@PathVariable UUID id, @RequestBody BoardUpdateRequestDto request) {
        BoardResponseDto board = boardService.updateBoardById(id, request);
        return ResponseEntity.ok().body(new StandardApiResponse<>("Board updated", board));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardApiResponse<Object>>
    deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
