package com.banking.customerservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.banking.customerservice.entity.*;

@Entity
@Table(name="[customer]", schema = "dbo")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private Long id;
    
    @Column(name = "SALUTATION")
    private String salutation;
    
    @Column(name = "FIRST_NAME")
    private String firstname;
    
    @Column(name = "LAST_NAME")
    private String lastname;
	
    @Column(name = "DOB")
    private LocalDate dob;
          
    @Column(name = "GENDER")
	private int gender;
    
    @Column(name = "RELIGION")
	private int religion;    
      
    @Column(name = "EMAIL")
	private String email;
    
    @Column(name = "PHONE")
	private String phone;
           
    @Column(name = "OCCUPATION")
	private int occupation;
    
    @Column(name = "ANNUAL_INCOME")
	private float annual_income;
    
    @Column(name = "MARITAL_STATUS")
	private int marital_status;
    
    @Column(name = "SPOUSE_NAME")
	private String spouse_name;
    
    @Column(name = "SPOUSE_OCCUPATION")
	private int spouse_occupation;
    
    @Column(name = "SPOUSE_INCOME")
	private float spouse_income;
    
    @Column(name = "SPOUSE_EDUCATION")
	private int spouse_education;   
 
    @Column(name = "keycloak_id", nullable = false, unique = true)
    private String keycloakId;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependant> dependents = new ArrayList<>();
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerEducation> customerEducation = new ArrayList<>();
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerIdentity> customerIdentity = new ArrayList<>();
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Dependant> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependant> dependents) {
		this.dependents = dependents;
	}

	public List<CustomerEducation> getCustomerEducation() {
		return customerEducation;
	}

	public void setCustomerEducation(List<CustomerEducation> customerEducation) {
		this.customerEducation = customerEducation;
	}

	public List<CustomerIdentity> getCustomerIdentity() {
		return customerIdentity;
	}

	public void setCustomerIdentity(List<CustomerIdentity> customerIdentity) {
		this.customerIdentity = customerIdentity;
	}

	public String getKeycloakId() {
		return keycloakId;
	}

	public void setKeycloakId(String keycloakId) {
		this.keycloakId = keycloakId;
	}

    
    }
