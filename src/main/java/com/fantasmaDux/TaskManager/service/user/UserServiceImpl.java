package com.fantasmaDux.TaskManager.service.user;

import com.fantasmaDux.TaskManager.api.dto.request.UserRegistrationRequestDto;
import com.fantasmaDux.TaskManager.api.dto.response.UserResponseDto;
import com.fantasmaDux.TaskManager.api.exception.UserNotFoundException;
import com.fantasmaDux.TaskManager.mapper.UserMapper;
import com.fantasmaDux.TaskManager.service.keycloak.KeycloakAdminService;
import com.fantasmaDux.TaskManager.store.model.UserEntity;
import com.fantasmaDux.TaskManager.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakAdminService keycloakAdminService;

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity userEntity
                = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return userMapper.userToUserResponseDto(userEntity);
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        String keycloakId = keycloakAdminService.createUserInKeycloak(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
        );

        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setKeycloakId(UUID.fromString(keycloakId));

        return userMapper.userToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto findOrCreateUser(String email, Jwt jwt) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setFirstName((String) jwt.getClaims().get("given_name"));
            userEntity.setLastName((String) jwt.getClaims().get("family_name"));
        }

        return userMapper.userToUserResponseDto(userRepository.save(userEntity));
    }

}
