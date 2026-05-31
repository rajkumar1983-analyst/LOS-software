package com.banking.productservice.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.banking.productservice.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class ProductDTO {
	
	@NotBlank(message = "Product Name must not be blank")
	private String product_name;
	private String product_description;
	
	@NotBlank(message = "Product Type is mandatory")
	private int product_type;
	
	@NotBlank(message = "Product sub type is mandatory")
	private int product_sub_type;
	
	@NotBlank(message = "Product Effective from is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime product_effective_from;
	
	@NotBlank(message = "Product Status is mandatory")
	private String product_status;
	
	
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductDTO(Product product) {
		this.product_description = product.getProduct_description();
		this.product_effective_from = product.getProduct_effective_from();
		this.product_name = product.getProduct_name();
		this.product_status = product.getProduct_status();
		this.product_type = product.getProduct_type();
		this.product_sub_type = product.getProduct_sub_type();
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
