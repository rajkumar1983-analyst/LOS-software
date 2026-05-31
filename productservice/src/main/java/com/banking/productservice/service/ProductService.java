package com.banking.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.productservice.DTO.ProductDTO;
import com.banking.productservice.entity.Product;
import com.banking.productservice.repository.ProductRepository;

@Service
public interface ProductService {
	ProductDTO addProduct(ProductDTO dto);
	ProductDTO getProductsbyProductId(Integer productId);
	List<ProductDTO> getAllProducts();
	
}
