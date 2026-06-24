package com.banking.loanservice.service;

import com.banking.loanservice.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    public CustomerDTO getCustomerById(Long customerId) {
        String url = customerServiceUrl + "/api/customers/" + customerId;
        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(url, CustomerDTO.class);
        return response.getBody();
    }
}
