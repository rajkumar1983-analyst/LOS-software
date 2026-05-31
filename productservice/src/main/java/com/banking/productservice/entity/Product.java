package com.banking.productservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="[product]", schema = "dbo")

public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

	
	private String product_name;
	private String product_description;
	private int product_type;
	private int product_sub_type;
	private LocalDateTime product_effective_from;
	private String product_status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public int getProduct_type() {
		return product_type;
	}
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	public int getProduct_sub_type() {
		return product_sub_type;
	}
	public void setProduct_sub_type(int product_sub_type) {
		this.product_sub_type = product_sub_type;
	}
	public LocalDateTime getProduct_effective_from() {
		return product_effective_from;
	}
	public void setProduct_effective_from(LocalDateTime product_effective_from) {
		this.product_effective_from = product_effective_from;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	} 
	
}
