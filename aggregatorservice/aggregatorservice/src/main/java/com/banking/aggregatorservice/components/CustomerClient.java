package com.banking.aggregatorservice.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.aggregatorservice.dto.CustomerDTO;
import com.banking.aggregatorservice.dto.LoanDTO;

@Component
public class CustomerClient {

	private final WebClient webClient;

	public CustomerClient(WebClient.Builder builder,
			@Value("${customer.service.url}") String customerServiceURI) {
		this.webClient = builder.baseUrl(customerServiceURI).build();
    }
	
	public List<CustomerDTO> getCustomers() {        
        
        return webClient.get()
                .uri("/api/customers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CustomerDTO>>() {})
                .block();

    }
	

	
}
