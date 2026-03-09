package com.fantasmaDux.TaskManager.api.http.server.controller;

import com.fantasmaDux.TaskManager.api.dto.response.StandardApiResponse;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardApiResponse<UserResponseDto>>
    getUserById(@PathVariable("id") UUID id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok().body(new StandardApiResponse<>("user found", user));
    }
}
