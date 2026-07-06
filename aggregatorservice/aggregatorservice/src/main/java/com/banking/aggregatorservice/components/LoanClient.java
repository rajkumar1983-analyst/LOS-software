package com.banking.aggregatorservice.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.aggregatorservice.dto.LoanDTO;

@Component
public class LoanClient {

	private final WebClient webClient;

    public LoanClient(WebClient.Builder builder,
    		@Value("${loan.service.url}") String loanServiceURI) {
    	this.webClient = builder.baseUrl(loanServiceURI).build();
    }

    public List<LoanDTO> getLoans() {

        // Capture the caller's bearer token on the request thread (where the
        // SecurityContext is available) and forward it explicitly, so the
        // downstream @PreAuthorize-protected endpoint authenticates as the
        // original user instead of returning 401.
        String token = currentBearerToken();

        return webClient.get()
                .uri("/api/loans")
                .headers(h -> { if (token != null) h.setBearerAuth(token); })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LoanDTO>>() {})
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
