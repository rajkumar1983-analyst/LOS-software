package com.banking.aggregatorservice.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
                
        return webClient.get()
                .uri("/api/loans")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LoanDTO>>() {})
                .block();
    }

}
