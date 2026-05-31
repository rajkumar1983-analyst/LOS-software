package com.banking.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.productservice.DTO.ProductInterestRateDTO;
import com.banking.productservice.entity.ProductInterestRate;
import com.banking.productservice.repository.ProductInterestRateRepository;
import com.banking.productservice.service.ProductInterestRateService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/products")
public class ProductInterestRateController {

    @Autowired
    private ProductInterestRateService productInterestRateService;

    @PostMapping("/{productId}/interest-rates")
    public ResponseEntity<String> addInterestRate(
            @PathVariable Integer productId,
            @RequestBody ProductInterestRateDTO dto) {

        productInterestRateService.addInterestRate(productId,dto);
        return ResponseEntity.ok("Interest rate added successfully.");
    }
    
    @GetMapping("/{productId}/interest-rates")
    public ResponseEntity<List<ProductInterestRateDTO>> getProductInterestRate(@PathVariable Integer productId)
    {
    	List<ProductInterestRateDTO> dto = productInterestRateService.getInterestRatesByProductId(productId);
    	return ResponseEntity.ok(dto);    	
    	
    }
}

