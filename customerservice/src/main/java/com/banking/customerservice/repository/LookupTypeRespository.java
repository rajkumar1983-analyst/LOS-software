package com.banking.customerservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.customerservice.entity.LookupType;

public interface LookupTypeRespository extends JpaRepository<LookupType,Integer> {
	Optional<LookupType> findByTypeCode(String code);
}
