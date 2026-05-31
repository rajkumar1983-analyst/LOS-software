package com.banking.loanservice.dto.projection;

import java.math.BigDecimal;

import jakarta.persistence.Column;

public interface LoanSummaryDTO {
	
	Long getId();	
    String getCustomerId();
    String getKeycloakId();
    BigDecimal getLoanAmount();    
    String getLoanType();    
    Integer getStatus();    
    Integer getIsUnderwritten();    
    Integer getIsApproved();

}
