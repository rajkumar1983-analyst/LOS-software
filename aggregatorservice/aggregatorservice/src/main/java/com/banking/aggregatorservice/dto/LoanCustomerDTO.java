package com.banking.aggregatorservice.dto;

import java.math.BigDecimal;

public class LoanCustomerDTO {
	
	private Long id;
	private Long customerId;   
	private String salutation; 
	private String firstname;
	private String lastname;
	private Integer gender;
    private BigDecimal loanAmount;    
    private String loanType;    
    private int status;    
    private int isUnderwritten;    
    private int isApproved;
           
	public LoanCustomerDTO(Long id, Long customerId, String salutation, String firstname, String lastname,
			Integer gender, BigDecimal loanAmount, String loanType, int status, int isUnderwritten, int isApproved) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.salutation = salutation;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.status = status;
		this.isUnderwritten = isUnderwritten;
		this.isApproved = isApproved;
	}
	
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
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
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
