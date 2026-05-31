package com.banking.customerservice.dto;

import java.time.LocalDate;

import com.banking.customerservice.entity.CustomerEducation;
import com.banking.customerservice.entity.CustomerIdentity;

import jakarta.validation.constraints.NotBlank;

public class CustIDDTO {
	@NotBlank(message = "ID Type is Mandatory")
	private int idtype;
	
	@NotBlank(message = "ID Value is Mandatory")
	private String identityValue;
	
	@NotBlank(message = "Valid Until is Mandatory")	
	private LocalDate validUntil;
	
	
	// Constructor to map from Entity
    public CustIDDTO(CustomerIdentity custIdentity) {    	
    	this.idtype = custIdentity.getIdtype();
    	this.identityValue = custIdentity.getIdentityValue();
    	this.validUntil = custIdentity.getValidUntil();
    }
	
    public CustIDDTO() {
    	
    }
	
	public int getIdtype() {
		return idtype;
	}
	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}
	public String getIdentityValue() {
		return identityValue;
	}
	public void setIdentityValue(String identityValue) {
		this.identityValue = identityValue;
	}
	public LocalDate getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(LocalDate validUntil) {
		this.validUntil = validUntil;
	}	
}
