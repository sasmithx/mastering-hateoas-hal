package com.sasmithx.hateoas.controller;

import com.sasmithx.hateoas.dto.AuthResponse;
import com.sasmithx.hateoas.dto.SigninRequest;
import com.sasmithx.hateoas.dto.SignupRequest;
import com.sasmithx.hateoas.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KeycloakUserService keycloakUserService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest req) {
        keycloakUserService.register(req.username(), req.password(), req.role());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SigninRequest req) {
        AuthResponse resp = keycloakUserService.authenticate(req.username(), req.password());
        return ResponseEntity.ok(resp);
    }
}

