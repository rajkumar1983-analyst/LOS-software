package com.banking.customerservice.dto;

import com.banking.customerservice.entity.CustomerEducation;
import com.banking.customerservice.entity.Dependant;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustEducationDTO {
	@NotNull(message = "Qualification is Mandatory")
	private int qualification;
	
	@NotBlank(message = "Institution is Mandatory")
	private String institution;
	
	@NotNull(message = "Completion Year is Mandatory")	
	private int completion_year;
	
	// Constructor to map from Entity
    public CustEducationDTO(CustomerEducation custEducation) {
        this.qualification = custEducation.getQualification();
        this.institution = custEducation.getInstitution();
        this.completion_year = custEducation.getCompletionYear();
    }
	
    public CustEducationDTO() {
    	
    }

	
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public int getCompletion_year() {
		return completion_year;
	}
	public void setCompletion_year(int completion_year) {
		this.completion_year = completion_year;
	}
}
