package com.banking.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.customerservice.entity.LookupEntity;
import com.banking.customerservice.entity.LookupType;

public interface LookupValueRepository extends JpaRepository<LookupEntity,Integer> {
	List<LookupEntity> findByTypeCode(Integer typeCode);
}
