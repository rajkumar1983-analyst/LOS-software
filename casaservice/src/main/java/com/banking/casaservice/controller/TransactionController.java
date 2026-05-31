package com.banking.casaservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.casaservice.dto.AccountDTO;
import com.banking.casaservice.dto.TransactionDTO;
import com.banking.casaservice.service.AccountService;
import com.banking.casaservice.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	TransactionService tranService;
	
	@PostMapping
    public ResponseEntity<TransactionDTO> create(@Valid @RequestBody TransactionDTO request) {    	
        return new ResponseEntity<>(tranService.createTransaction(request), HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}") 
	public ResponseEntity<Optional<TransactionDTO>> get(@PathVariable int  id) {	  
		  return ResponseEntity.ok(tranService.getTransactionbyID(id));	  
	}	  
	
	  
	@GetMapping public ResponseEntity<List<TransactionDTO>> get() {
		  return ResponseEntity.ok().body(tranService.getAllTransactions()); }

	
}
