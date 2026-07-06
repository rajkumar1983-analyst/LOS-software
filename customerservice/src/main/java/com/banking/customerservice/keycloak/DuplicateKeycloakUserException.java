package com.banking.customerservice.keycloak;

/** Thrown when Keycloak rejects user creation because the username/email already exists. */
public class DuplicateKeycloakUserException extends RuntimeException {
    public DuplicateKeycloakUserException(String message) {
        super(message);
    }
}
