package com.banking.casaservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.casaservice.dto.AccountDTO;
import com.banking.casaservice.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO request) {    	
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

	
	@GetMapping("/{id}") 
	public ResponseEntity<Optional<AccountDTO>> get(@PathVariable int  id) {	  
		  return ResponseEntity.ok(accountService.getAccountbyID(id));	  
	}	  
	
	  
	@GetMapping public ResponseEntity<List<AccountDTO>> get() {
		  return ResponseEntity.ok().body(accountService.getAllAccounts()); }
	}
