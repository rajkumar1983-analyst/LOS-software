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

@Service
public class DashboardService {
	private final LoanClient loanClient;
    private final CustomerClient customerClient;

    public DashboardService(LoanClient loanClient, CustomerClient customerClient) {
        this.loanClient = loanClient;
        this.customerClient = customerClient;
    }

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

    	                if (customer == null) {
    	                    throw new RuntimeException("Customer not found: " + customerId);
    	                }

    	                return new LoanCustomerDTO(
    	                        loan.getId(),
    	                        customerId,
    	                        customer.getSalutation(),
    	                        customer.getFirstname(),
    	                        customer.getLastname(),
    	                        customer.getGender(),
    	                        loan.getLoanAmount(),
    	                        loan.getLoanType(),
    	                        loan.getStatus(),
    	                        loan.getIsUnderwritten(),
    	                        loan.getIsApproved()
    	                );
    	            })
    	            .collect(Collectors.toList());
    	}    	 
    }

