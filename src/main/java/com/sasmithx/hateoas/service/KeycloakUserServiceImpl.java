package com.sasmithx.hateoas.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasmithx.hateoas.dto.AuthResponse;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final Keycloak keycloak;

    public KeycloakUserServiceImpl() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:5555")
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();
    }

    @Override
    public void createUser(String username, String password, String role) {

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);

        Response response = keycloak.realm("product-platform")
                .users()
                .create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        keycloak.realm("product-platform")
                .users()
                .get(userId)
                .resetPassword(credential);

        RoleRepresentation roleRep = keycloak.realm("product-platform")
                .roles()
                .get(role)
                .toRepresentation();

        keycloak.realm("product-platform")
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(roleRep));
    }

    @Override
    public void register(String username, String password, String role) {
        // for now reuse createUser
        createUser(username, password, role);
    }

    @Override
    public AuthResponse authenticate(String username, String password) {
        try {
            String tokenUrl = "http://localhost:5555/realms/product-platform/protocol/openid-connect/token";
            String clientId = "product-client"; // Assumption: a public client that supports password grant

            String form = "grant_type=password&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                    + "&username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)
                    + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to authenticate user: " + response.body());
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.body());
            String accessToken = node.path("access_token").asText(null);
            String tokenType = node.path("token_type").asText(null);
            Long expiresIn = node.has("expires_in") ? node.path("expires_in").asLong() : null;
            String refreshToken = node.path("refresh_token").asText(null);

            return new AuthResponse(accessToken, tokenType, expiresIn, refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("Error authenticating user", e);
        }
    }
}
