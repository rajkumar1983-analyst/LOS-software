package com.banking.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customerservice.entity.LookupEntity;
import com.banking.customerservice.entity.LookupType;
import com.banking.customerservice.repository.LookupTypeRespository;
import com.banking.customerservice.repository.LookupValueRepository;

@Service
public class LookupService {

	@Autowired
	private LookupTypeRespository typeRepo;
	@Autowired
	private LookupValueRepository valueRepo;

    public List<LookupEntity> getValues(String typeCode) {
    	
    	LookupType type = typeRepo.findByTypeCode(typeCode)
    						.orElseThrow();    						
        return valueRepo.findByTypeCode(type.getId());
       }

	
}
