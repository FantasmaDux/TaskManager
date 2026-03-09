package com.fantasmaDux.TaskManager.api.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UUID> boardIds;
    private List<UUID> taskIds;
}
