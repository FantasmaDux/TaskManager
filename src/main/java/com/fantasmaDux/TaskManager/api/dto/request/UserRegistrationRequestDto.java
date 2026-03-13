package com.fantasmaDux.TaskManager.api.dto.request;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
