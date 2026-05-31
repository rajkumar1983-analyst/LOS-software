package com.banking.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.productservice.DTO.ProductDTO;
import com.banking.productservice.entity.Product;
import com.banking.productservice.repository.ProductRepository;
import com.banking.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productrepository;
	
	@Override
	public ProductDTO addProduct(ProductDTO dto)
	{
		Product product = new Product();
		product.setProduct_description(dto.getProduct_description());
		product.setProduct_effective_from(dto.getProduct_effective_from());
		product.setProduct_name(dto.getProduct_name());
		product.setProduct_status(dto.getProduct_status());
		product.setProduct_type(dto.getProduct_type());
		product.setProduct_sub_type(dto.getProduct_sub_type());
		productrepository.save(product);
		return dto;
	}
	
	private ProductDTO maptoDTO(Product product)
	{
		ProductDTO dto = new ProductDTO();
		dto.setProduct_description(product.getProduct_description());
		dto.setProduct_effective_from(product.getProduct_effective_from());
		dto.setProduct_name(product.getProduct_name());
		dto.setProduct_type(product.getProduct_type());
		dto.setProduct_sub_type(product.getProduct_sub_type());
		dto.setProduct_status(product.getProduct_status());
		return dto;
		
	}
	
	@Override
	public ProductDTO getProductsbyProductId(Integer productId)
	{
		Product product = productrepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
		ProductDTO dto = new ProductDTO();
		dto.setProduct_description(product.getProduct_description());
		dto.setProduct_effective_from(product.getProduct_effective_from());
		dto.setProduct_name(product.getProduct_name());
		dto.setProduct_status(product.getProduct_status());
		dto.setProduct_sub_type(product.getProduct_sub_type());
		dto.setProduct_type(product.getProduct_type());
		
		return dto;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		return productrepository.findAll()
				.stream()
				.map(ProductDTO::new)
				.toList();			
		
	}

}
