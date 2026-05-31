package com.banking.customerservice.dto;

import com.banking.customerservice.entity.Dependant;

public class DependantDTO {
	private String name;
	private String relationship;
	private int age;
	
	// Constructor to map from Entity
    public DependantDTO(Dependant dependant) {
    	this.name = dependant.getName();
        this.relationship = dependant.getRelationship();
        this.age = dependant.getAge();
    }
	
    public DependantDTO() {
    	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
