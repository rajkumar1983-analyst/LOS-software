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
@Table(name="[cust_dependant]", schema = "dbo")
public class Dependant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dependant_id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "dependant_relation")
    private String relationship;
	
    @Column(name = "dependant_age")
    private int age;
    
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
    }

