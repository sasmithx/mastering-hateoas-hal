package com.sasmithx.hateoas.service;

import com.sasmithx.hateoas.dto.AuthResponse;

public interface KeycloakUserService {
    // Create a Keycloak user with username, password and role
    void createUser(String username, String password, String role);

    // Register user (wrapper around createUser)
    void register(String username, String password, String role);

    // Authenticate using resource owner password grant and return tokens
    AuthResponse authenticate(String username, String password);
}
