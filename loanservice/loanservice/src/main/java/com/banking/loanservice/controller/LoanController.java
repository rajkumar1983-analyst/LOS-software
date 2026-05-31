package com.banking.loanservice.controller;

import java.util.Optional;
import com.banking.loanservice.exception.LoanNotFoundException;
import com.banking.loanservice.entity.Loan;
import com.banking.loanservice.service.CustomerClientService;
import com.banking.loanservice.service.LoanService;
import com.banking.loanservice.dto.*;
import com.banking.loanservice.dto.projection.LoanSummaryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private CustomerClientService customerClientService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_APPLICATION')")
    public Loan createLoan(@RequestBody Loan loan,@AuthenticationPrincipal Jwt jwt) {
    	loan.setKeycloakId(jwt.getSubject());
        return loanService.createLoan(loan);
    }
    
    @PostMapping("/{id}/underwrite")
    @PreAuthorize("hasAuthority('UNDERWRITE')")
    public ResponseEntity underwriteLoan(@RequestBody UnderwriteDTO underwritedto)
    {
    	loanService.underwriteLoan(underwritedto);
    	return ResponseEntity.ok("Underwriting Successful");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('APPLICATION_ALL_LIST_VIEW')")
    public List<LoanSummaryDTO> getAllLoans() {
    	return loanService.getAllLoans();
    }
    
    @GetMapping("/mine")
    @PreAuthorize("hasAuthority('APPLIED_LIST_VIEW')")
    public List<LoanSummaryDTO> getMyLoans(@AuthenticationPrincipal Jwt jwt) {
        return loanService.getLoansbykeycloakId(jwt.getSubject());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('APPLICATION_ALL_LIST_VIEW') or hasAuthority('APPLIED_LIST_VIEW')")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
            	
    	Loan loan = loanService.getLoanById(id);
    	return ResponseEntity.ok(loan);
                
    }
    
    @GetMapping("/check-customer/{customerId}")
    @PreAuthorize("hasAuthority('APPLICATION_ALL_LIST_VIEW') or hasAuthority('APPLIED_LIST_VIEW')")    
    public ResponseEntity<CustomerDTO> fetchCustomer(@PathVariable Long customerId) {
        CustomerDTO customer = customerClientService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
}

