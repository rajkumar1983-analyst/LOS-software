package com.banking.loanservice.service;

import com.banking.loanservice.dto.UnderwriteDTO;
import com.banking.loanservice.dto.projection.LoanSummaryDTO;
import com.banking.loanservice.entity.Loan;
import java.util.Optional;
import com.banking.loanservice.exception.LoanNotFoundException;
import com.banking.loanservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<LoanSummaryDTO> getAllLoans() {
        return loanRepository.findAllBy();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan with ID " + id + " not found"));
    }
    
    public List<LoanSummaryDTO> getLoansbykeycloakId(String keycloakId) {
    	return loanRepository.findAllByKeycloakId(keycloakId);
    }
    
    public Loan underwriteLoan(UnderwriteDTO underwritedto) {
    	Loan loan = loanRepository.findById(underwritedto.getId()).orElseThrow(()->new RuntimeException("Loan Not Found"));
    	loan.setUnderwriterComments(underwritedto.getComments());
    	loan.setIsUnderwritten(underwritedto.getDecision());
    	loan.setStatus(2);
    	return loanRepository.save(loan);
    	
    }
}

