package com.banking.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.productservice.entity.ProductInterestRate;
import java.util.*;

public interface ProductInterestRateRepository extends JpaRepository<ProductInterestRate, Integer> {

	List<ProductInterestRate> findByProductIdAndIsActiveTrue(Integer productId);
}
