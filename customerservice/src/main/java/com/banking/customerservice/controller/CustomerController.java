package com.banking.customerservice.controller;

import com.banking.customerservice.entity.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.banking.customerservice.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import com.banking.customerservice.dto.*;
import com.banking.customerservice.dto.projection.CustomerSummaryDTO;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_APPLICATION')")
    public ResponseEntity<Customer> create(@Valid @RequestBody CustomerRequest request,@AuthenticationPrincipal Jwt jwt) {
    	request.setKeycloakId(jwt.getSubject());
        return new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('APPLICATION_ALL_LIST_VIEW') or hasAuthority('APPLIED_LIST_VIEW')")
    public CustomerRequest get(@PathVariable Long id,@AuthenticationPrincipal Jwt jwt,
            Authentication auth) {
    	
    	CustomerRequest c = customerService.getCustomerById(id);
    	boolean canSeeAll = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("APPLICATION_ALL_LIST_VIEW"));
            if (!canSeeAll && !jwt.getSubject().equals(c.getKeycloakId())) {
                throw new AccessDeniedException("Not your customer record");
            }
    	return c;    	
    }
   
  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	
    	customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('APPLICATION_ALL_LIST_VIEW')")
    public ResponseEntity<List<CustomerSummaryDTO>> getSummary() {
    	
    	return ResponseEntity.ok(customerService.getCustomerSummary());
    	
    }
    
    //@GetMapping
    //public ResponseEntity<List<CustomerRequest>> get() {
    	
    //    return ResponseEntity.ok().body(customerService.getAllCustomers());
    //}  
    

}
