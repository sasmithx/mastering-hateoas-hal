package com.sasmithx.hateoas.controller;

import com.sasmithx.hateoas.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final KeycloakUserService keycloakUserService;

    public record UserRequest(String username, String password, String role) {}

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createUser(@RequestBody  UserRequest req) {
        keycloakUserService.createUser(req.username(), req.password(), req.role());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
