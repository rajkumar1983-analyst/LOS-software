package com.banking.productservice.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class ProductInterestRateDTO {

    @NotBlank(message = "Product ID is mandatory")
	private Integer productId;
    
    @NotBlank(message = "Min score is mandatory")   
    private Integer minScore;
    
    @NotBlank(message = "Max score is mandatory")    
    private Integer maxScore;
    
    @NotBlank(message = "Interest is mandatory")    
    private Double interestRate;
    
    @NotBlank(message = "Interest Effective from is mandatory")    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    
    private LocalDateTime effectiveFrom;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getMinScore() {
		return minScore;
	}
	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public LocalDateTime getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(LocalDateTime effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
 
}
