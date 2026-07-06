package com.banking.aggregatorservice.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.aggregatorservice.dto.CustomerDTO;

@Component
public class CustomerClient {

	private final WebClient webClient;

	public CustomerClient(WebClient.Builder builder,
			@Value("${customer.service.url}") String customerServiceURI) {
		this.webClient = builder.baseUrl(customerServiceURI).build();
    }

	public List<CustomerDTO> getCustomers() {

        // Capture the caller's bearer token on the request thread (where the
        // SecurityContext is available) and forward it explicitly, so the
        // downstream @PreAuthorize-protected endpoint authenticates as the
        // original user instead of returning 401.
        String token = currentBearerToken();

        return webClient.get()
                .uri("/api/customers")
                .headers(h -> { if (token != null) h.setBearerAuth(token); })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CustomerDTO>>() {})
                .block();

    }

    private String currentBearerToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getTokenValue();
        }
        return null;
    }

}
