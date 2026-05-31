package com.banking.productservice.service;

import com.banking.productservice.DTO.ProductInterestRateDTO;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProductInterestRateService {
	ProductInterestRateDTO addInterestRate(Integer productId, ProductInterestRateDTO dto);    
    List<ProductInterestRateDTO> getInterestRatesByProductId(Integer productId);
}

