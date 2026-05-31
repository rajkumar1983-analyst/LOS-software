package com.banking.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.productservice.DTO.ProductDTO;
import com.banking.productservice.entity.Product;
import com.banking.productservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@PostMapping
	public ResponseEntity<ProductDTO> addProduct(ProductDTO dto)
	{		
		return new ResponseEntity<>(productservice.addProduct(dto),HttpStatus.CREATED);		
	}
	
	@GetMapping("/")
	public ResponseEntity <List<ProductDTO>> getAllProducts()
	{		
		return ResponseEntity.ok(productservice.getAllProducts());
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductbyId(@PathVariable int productId)
	{
		ProductDTO product = productservice.getProductsbyProductId(productId);
		return ResponseEntity.ok(product);
	}
}
