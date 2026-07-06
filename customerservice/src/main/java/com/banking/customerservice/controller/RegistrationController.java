package com.banking.customerservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customerservice.dto.CustomerRequest;
import com.banking.customerservice.dto.RegisterRequest;
import com.banking.customerservice.entity.Customer;
import com.banking.customerservice.keycloak.KeycloakAdminService;
import com.banking.customerservice.service.CustomerService;

import jakarta.validation.Valid;

/**
 * Public self-service registration: provisions a Keycloak user (with password and the
 * APPLICANT role) and creates the matching customer profile bound to that user's id.
 */
@RestController
@RequestMapping("/api/customers/register")
public class RegistrationController {

    private final KeycloakAdminService keycloakAdminService;
    private final CustomerService customerService;

    public RegistrationController(KeycloakAdminService keycloakAdminService,
                                  CustomerService customerService) {
        this.keycloakAdminService = keycloakAdminService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request) {
        CustomerRequest profile = request.getProfile();

        // 1) Provision the Keycloak user (sets password + APPLICANT role).
        String keycloakId = keycloakAdminService.createApplicant(
                request.getUsername(),
                profile.getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                request.getPassword());

        // 2) Create the customer profile bound to the new user. If this fails, roll back
        //    the Keycloak user so a failed registration leaves nothing half-created.
        try {
            profile.setKeycloakId(keycloakId);
            Customer customer = customerService.createCustomer(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "customerId", customer.getId(),
                    "keycloakId", keycloakId));
        } catch (RuntimeException ex) {
            keycloakAdminService.deleteUserQuietly(keycloakId);
            throw ex;
        }
    }
}
