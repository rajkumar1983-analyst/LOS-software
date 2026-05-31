package com.banking.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.productservice.DTO.ProductInterestRateDTO;
import com.banking.productservice.entity.ProductInterestRate;
import com.banking.productservice.repository.ProductInterestRateRepository;
import com.banking.productservice.service.ProductInterestRateService;

@Service
public class ProductInterestRateServiceImpl implements ProductInterestRateService {

    @Autowired
    private ProductInterestRateRepository repository;

    @Override
    public ProductInterestRateDTO addInterestRate(Integer productId, ProductInterestRateDTO dto) {
        ProductInterestRate rate = new ProductInterestRate();
        rate.setProductId(productId);
        rate.setMinScore(dto.getMinScore());
        rate.setMaxScore(dto.getMaxScore());
        rate.setInterestRate(dto.getInterestRate());
        rate.setEffectiveFrom(dto.getEffectiveFrom());
        rate.setIsActive(true);

        repository.save(rate);
        return dto;
    }
    
    @Override
    public List<ProductInterestRateDTO> getInterestRatesByProductId(Integer productId)
    {
    	return repository.findByProductIdAndIsActiveTrue(productId)
                .stream()
                .map(rate -> {
                    ProductInterestRateDTO dto = new ProductInterestRateDTO();
                    dto.setProductId(rate.getProductId());
                    dto.setMinScore(rate.getMinScore());
                    dto.setMaxScore(rate.getMaxScore());
                    dto.setInterestRate(rate.getInterestRate());
                    dto.setEffectiveFrom(rate.getEffectiveFrom());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    
}
