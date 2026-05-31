package com.banking.loanservice.repository;

import com.banking.loanservice.dto.projection.LoanSummaryDTO;
import com.banking.loanservice.entity.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	List<LoanSummaryDTO> findAllBy();
	
	List<LoanSummaryDTO> findAllByKeycloakId(String keycloakId);
}

