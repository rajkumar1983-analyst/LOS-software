package com.banking.loanservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "[loan]",schema="dbo")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id")
    private Long customerId;
    
    @Column(name = "loan_amount")
    private BigDecimal loanAmount;
    
    @Column(name = "loan_type")
    private String loanType;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "term_in_months")
    private Integer termInMonths;
    
    private Integer status;
    
    //Underwriter Fields
    @Column(name = "underwritercomments")
    private String underwriterComments;
    
    @Column(name = "isunderwritten")
    private Integer isUnderwritten;
    
    //Approver Fields
    @Column(name = "approvercomments")
    private String approverComments;
    
    @Column(name = "isapproved")
    private Integer isApproved;

    @Column(name = "keycloak_id", nullable = false)
    private String keycloakId;
    
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Integer getTermInMonths() {
		return termInMonths;
	}

	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUnderwriterComments() {
		return underwriterComments;
	}

	public void setUnderwriterComments(String underwriterComments) {
		this.underwriterComments = underwriterComments;
	}

	public Integer getIsUnderwritten() {
		return isUnderwritten;
	}

	public void setIsUnderwritten(Integer isUnderwritten) {
		this.isUnderwritten = isUnderwritten;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public String getKeycloakId() {
		return keycloakId;
	}

	public void setKeycloakId(String keycloakId) {
		this.keycloakId = keycloakId;
	}
    
	 
}

