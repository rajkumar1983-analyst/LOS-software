package com.banking.customerservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * Self-service registration payload: login credentials plus the full applicant profile.
 * The profile is created as a customer row once the Keycloak user is provisioned.
 */
public class RegisterRequest {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Valid
    @jakarta.validation.constraints.NotNull(message = "Profile is mandatory")
    private CustomerRequest profile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerRequest getProfile() {
        return profile;
    }

    public void setProfile(CustomerRequest profile) {
        this.profile = profile;
    }
}
