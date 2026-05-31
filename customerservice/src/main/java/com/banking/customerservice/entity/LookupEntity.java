package com.banking.customerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LOOKUP_VALUES", schema = "dbo")
public class LookupEntity {
	
	@Id
	@Column(name="ID")
	private Integer id;
		
	@Column(name = "TYPE_CODE")	
	private Integer typeCode;
	
	@Column(name="VALUE_CODE")
	private Integer valueCode;
	
	@Column(name="VALUE_DESC")
	private String valueDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValueCode() {
		return valueCode;
	}

	public void setValueCode(int valueCCode) {
		this.valueCode = valueCode;
	}

	public String getValueDesc() {
		return valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	
}
