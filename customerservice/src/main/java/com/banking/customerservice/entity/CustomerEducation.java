package com.banking.customerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="[cust_education]", schema = "dbo")
public class CustomerEducation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edu_id")
	private int id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
	
	@Column(name="qualification")
	private int qualification;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="completion_year")
	private int completionYear;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public int getCompletionYear() {
		return completionYear;
	}

	public void setCompletionYear(int completionYear) {
		this.completionYear = completionYear;
	}

}
