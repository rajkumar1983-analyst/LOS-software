package com.banking.aggregatorservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banking.aggregatorservice.components.CustomerClient;
import com.banking.aggregatorservice.components.LoanClient;
import com.banking.aggregatorservice.dto.CustomerDTO;
import com.banking.aggregatorservice.dto.LoanCustomerDTO;
import com.banking.aggregatorservice.dto.LoanDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DashboardService {
	private final LoanClient loanClient;
    private final CustomerClient customerClient;

    public DashboardService(LoanClient loanClient, CustomerClient customerClient) {
        this.loanClient = loanClient;
        this.customerClient = customerClient;
    }

    @CircuitBreaker(name = "aggregator", fallbackMethod = "getLoansWithCustomersFallback")
    public List<LoanCustomerDTO> getLoansWithCustomers() {

    	    List<LoanDTO> loans = loanClient.getLoans();

    	    List<CustomerDTO> customers = customerClient.getCustomers();
    	    // Ideally: getCustomersByIds(customerIds)

    	    Map<Long, CustomerDTO> customerMap =
    	            customers.stream()
    	            .collect(Collectors.toMap(CustomerDTO::getId, c -> c));

    	    return loans.stream()
    	            .map(loan -> {

    	                Long customerId = loan.getCustomerId();
    	                CustomerDTO customer = customerMap.get(customerId);

    	                // A missing customer is a data gap, not a dependency outage: show the loan
    	                // with placeholder customer fields rather than failing the whole dashboard
    	                // (which would also wrongly trip the circuit breaker).
    	                return new LoanCustomerDTO(
    	                        loan.getId(),
    	                        customerId,
    	                        customer != null ? customer.getSalutation() : null,
    	                        customer != null ? customer.getFirstname() : "N/A",
    	                        customer != null ? customer.getLastname() : null,
    	                        customer != null ? customer.getGender() : null,
    	                        loan.getLoanAmount(),
    	                        loan.getLoanType(),
    	                        loan.getStatus(),
    	                        loan.getIsUnderwritten(),
    	                        loan.getIsApproved()
    	                );
    	            })
    	            .collect(Collectors.toList());
    	}

    // Invoked when the loan or customer service is unavailable (or the breaker is OPEN).
    // Degrades gracefully: returns an empty dashboard instead of failing the request.
    public List<LoanCustomerDTO> getLoansWithCustomersFallback(Throwable t) {
        System.out.println("AGGREGATOR FALLBACK : dashboard degraded -> " + t.getClass().getSimpleName()
                + ": " + t.getMessage());
        return List.of();
    }
    }

