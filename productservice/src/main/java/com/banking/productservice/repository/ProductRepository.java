package com.banking.productservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.productservice.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findById(Integer productId);
	List<Product> findAll();
}
