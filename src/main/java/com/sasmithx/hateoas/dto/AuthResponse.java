package com.sasmithx.hateoas.dto;

public record AuthResponse(String accessToken, String tokenType, Long expiresIn, String refreshToken) {
}

