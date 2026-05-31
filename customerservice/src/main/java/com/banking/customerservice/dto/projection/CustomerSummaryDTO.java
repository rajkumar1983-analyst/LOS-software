package com.banking.customerservice.dto.projection;

public interface CustomerSummaryDTO {
	Long getId();
	String getSalutation(); 
	String getFirstname();
	String getLastname();
	Integer getGender();
}
