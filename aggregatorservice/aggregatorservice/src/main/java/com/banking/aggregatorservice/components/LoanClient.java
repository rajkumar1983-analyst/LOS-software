package com.banking.aggregatorservice.components;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.aggregatorservice.dto.LoanDTO;

@Component
public class LoanClient {
	
	private final WebClient webClient;

    public LoanClient(WebClient.Builder builder) {
    	this.webClient = builder.baseUrl("http://localhost:8082").build();
    }

    public List<LoanDTO> getLoans() {
                
        return webClient.get()
                .uri("/api/loans")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LoanDTO>>() {})
                .block();
    }

}
