package com.banking.customerservice.entity;

import java.time.LocalDate;

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
@Table(name="[CUST_IDENTITY]",schema="dbo")
public class CustomerIdentity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDENTITY_ID")
	private int id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "cust_id")
	private Customer customer;
	
	@Column(name="IDENTITY_TYPE")
	private int idtype;
	
	@Column(name="IDENTITY_VALUE")
	private String identityValue;
	
	@Column(name="VALID_UNTIL")
	private LocalDate validUntil;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCust_id() {
		return customer;
	}

	public void setCust_id(Customer customer) {
		this.customer = customer;
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
