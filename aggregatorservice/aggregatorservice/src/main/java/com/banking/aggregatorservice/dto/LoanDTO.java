package com.banking.aggregatorservice.dto;

import java.math.BigDecimal;

public class LoanDTO {
	private Long id;
	private Long customerId;    
    private BigDecimal loanAmount;    
    private String loanType;    
    private int status;    
    private int isUnderwritten;    
    private int isApproved;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsUnderwritten() {
		return isUnderwritten;
	}
	public void setIsUnderwritten(int isUnderwritten) {
		this.isUnderwritten = isUnderwritten;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

}
