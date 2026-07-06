package com.banking.customerservice.keycloak;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

/**
 * Provisions self-service applicants in Keycloak via the Admin REST API:
 * creates the user with a password and assigns the APPLICANT (composite) client role.
 */
@Service
public class KeycloakAdminService {

    private final RestClient rest = RestClient.create();

    @Value("${keycloak.admin.server-url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.admin.client-id}")
    private String adminClientId;
    @Value("${keycloak.admin.username}")
    private String adminUsername;
    @Value("${keycloak.admin.password}")
    private String adminPassword;
    @Value("${keycloak.client-id}")
    private String appClientId;
    @Value("${keycloak.applicant-role}")
    private String applicantRole;

    /**
     * Creates the user and assigns the applicant role.
     *
     * @return the new Keycloak user id (the JWT {@code sub})
     */
    public String createApplicant(String username, String email, String firstName,
                                  String lastName, String password) {
        String token = adminToken();
        String userId = createUser(token, username, email, firstName, lastName, password);
        try {
            assignApplicantRole(token, userId);
        } catch (RuntimeException ex) {
            // Role assignment failed: don't leave a half-provisioned user behind.
            deleteUserQuietly(token, userId);
            throw ex;
        }
        return userId;
    }

    /** Best-effort cleanup so a failed registration doesn't orphan a Keycloak user. */
    public void deleteUserQuietly(String userId) {
        try {
            deleteUserQuietly(adminToken(), userId);
        } catch (RuntimeException ignored) {
            // already logged / nothing actionable
        }
    }

    private String adminToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", adminClientId);
        form.add("username", adminUsername);
        form.add("password", adminPassword);
        form.add("grant_type", "password");

        Map<String, Object> resp = rest.post()
                .uri(serverUrl + "/realms/master/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {});

        if (resp == null || resp.get("access_token") == null) {
            throw new IllegalStateException("Could not obtain Keycloak admin token");
        }
        return (String) resp.get("access_token");
    }

    private String createUser(String token, String username, String email, String firstName,
                              String lastName, String password) {
        Map<String, Object> body = Map.of(
                "username", username,
                "email", email,
                "firstName", firstName,
                "lastName", lastName,
                "enabled", true,
                "emailVerified", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", password,
                        "temporary", false)));

        try {
            ResponseEntity<Void> resp = rest.post()
                    .uri(serverUrl + "/admin/realms/" + realm + "/users")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

            URI location = resp.getHeaders().getLocation();
            if (location == null) {
                throw new IllegalStateException("Keycloak did not return the created user location");
            }
            String path = location.getPath();
            return path.substring(path.lastIndexOf('/') + 1);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 409) {
                throw new DuplicateKeycloakUserException(
                        "A user with this username or email already exists");
            }
            throw ex;
        }
    }

    private void assignApplicantRole(String token, String userId) {
        String clientUuid = clientUuid(token);

        Map<String, Object> roleRep = rest.get()
                .uri(serverUrl + "/admin/realms/" + realm + "/clients/" + clientUuid
                        + "/roles/" + applicantRole)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {});

        rest.post()
                .uri(serverUrl + "/admin/realms/" + realm + "/users/" + userId
                        + "/role-mappings/clients/" + clientUuid)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(List.of(roleRep))
                .retrieve()
                .toBodilessEntity();
    }

    private String clientUuid(String token) {
        List<Map<String, Object>> clients = rest.get()
                .uri(serverUrl + "/admin/realms/" + realm + "/clients?clientId=" + appClientId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<Map<String, Object>>>() {});

        if (clients == null || clients.isEmpty()) {
            throw new IllegalStateException("Keycloak client not found: " + appClientId);
        }
        return (String) clients.get(0).get("id");
    }

    private void deleteUserQuietly(String token, String userId) {
        rest.method(org.springframework.http.HttpMethod.DELETE)
                .uri(serverUrl + "/admin/realms/" + realm + "/users/" + userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> { /* best effort */ })
                .toBodilessEntity();
    }
}
