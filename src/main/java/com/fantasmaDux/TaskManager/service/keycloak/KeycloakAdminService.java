package com.fantasmaDux.TaskManager.service.keycloak;

import org.keycloak.admin.client.Keycloak;

import java.util.UUID;

public interface KeycloakAdminService {
    Keycloak getKeycloakClient();
    String createUserInKeycloak(String email, String password, String firstName, String lastName);
    void deleteUserFromKeycloak(UUID userId);
}
