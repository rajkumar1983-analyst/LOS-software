package com.banking.customerservice.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.banking.customerservice.entity.Customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CustomerRequest {
	@NotBlank(message = "Salutation is Mandatory")
	private String salutation;
	
	@NotBlank(message = "First Name is Mandatory")
	private String firstName;
	
	@NotBlank(message = "Last Name is Mandatory")
	private String lastName;
	
	@NotNull(message = "Date of Birth is Mandatory")
	private LocalDate dob;	
	
	@NotNull(message = "Gender is Mandatory")
	private int gender;
	
	@NotNull(message = "Religion is Mandatory")
	private int religion;
	
	@NotBlank(message = "Email is Mandatory")
	private String email;
	
	@NotBlank(message = "Phone is Mandatory")
	private String phone;	
	
	@NotNull(message = "Occupation is Mandatory")
	private int occupation;
	
	@NotNull(message = "Income is Mandatory")
	@Positive
	private float annual_income;
	
	@NotNull(message = "Marital Status is Mandatory")
	private int marital_status;
		
	private String spouse_name;
	private int spouse_occupation;
	private float spouse_income;
	private int spouse_education;
	
	private String keycloakId;
	
		@Valid
	private List<DependantDTO> dependants;
	//Education DTO
	@Valid
	private List<CustEducationDTO> education;
	//ID Type DTO
	@Valid
	private List<CustIDDTO> identity;
	
	public CustomerRequest() {
		
	}
	
	public CustomerRequest(Customer customer) {
		this.salutation = customer.getSalutation();
		this.firstName = customer.getFirstname();
		this.lastName = customer.getLastname();
		this.dob = customer.getDob();		
	    this.gender = customer.getGender();
	    this.religion = customer.getReligion();	    
	    this.email = customer.getEmail();
	    this.phone = customer.getPhone();	    
	    this.occupation = customer.getOccupation();
	    this.annual_income = customer.getAnnual_income();
	    this.marital_status = customer.getMarital_status();
	    this.spouse_name = customer.getSpouse_name();
	    this.spouse_occupation = customer.getSpouse_occupation();
	    this.spouse_income = customer.getSpouse_income();
	    this.spouse_education = customer.getSpouse_education();
	    this.keycloakId = customer.getKeycloakId();
	    
	 // Map dependants from Entity to DTO
	    if (customer.getDependents() != null) {
	        this.dependants = customer.getDependents()
	            .stream()
	            .map(DependantDTO::new)
	            .toList();
	    }
	    //Map education from entity to DTO
	    if (customer.getCustomerEducation()!=null) {
	    	this.education = customer.getCustomerEducation()
	    			.stream()
	    			.map(CustEducationDTO::new)
	    			.toList();
	    }
	   // Map Identity from entity to DTO
	    if (customer.getCustomerIdentity()!=null) {
	    	this.identity = customer.getCustomerIdentity()
	    			.stream()
	    			.map(CustIDDTO::new)
	    			.toList();
	    }
	}
	
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	public String getLastname() {
		return lastName;
	}
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getReligion() {
		return religion;
	}
	public void setReligion(int religion) {
		this.religion = religion;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getOccupation() {
		return occupation;
	}
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}
	public float getAnnual_income() {
		return annual_income;
	}
	public void setAnnual_income(float annual_income) {
		this.annual_income = annual_income;
	}
	public int getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(int marital_status) {
		this.marital_status = marital_status;
	}
	public String getSpouse_name() {
		return spouse_name;
	}
	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}
	public int getSpouse_occupation() {
		return spouse_occupation;
	}
	public void setSpouse_occupation(int spouse_occupation) {
		this.spouse_occupation = spouse_occupation;
	}
	public float getSpouse_income() {
		return spouse_income;
	}
	public void setSpouse_income(float spouse_income) {
		this.spouse_income = spouse_income;
	}
	public int getSpouse_education() {
		return spouse_education;
	}
	public void setSpouse_education(int spouse_education) {
		this.spouse_education = spouse_education;
	}
	public List<DependantDTO> getDependants() {
		return dependants;
	}
	public void setDependants(List<DependantDTO> dependants) {
		this.dependants = dependants;
	}

	public List<CustEducationDTO> getEducation() {
		return education;
	}

	public void setEducation(List<CustEducationDTO> education) {
		this.education = education;
	}

	public List<CustIDDTO> getIdentity() {
		return identity;
	}

	public void setIdentity(List<CustIDDTO> identity) {
		this.identity = identity;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getKeycloakId() {
		return keycloakId;
	}

	public void setKeycloakId(String keycloakId) {
		this.keycloakId = keycloakId;
	}


	
}
