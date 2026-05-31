package com.banking.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customerservice.entity.LookupEntity;
import com.banking.customerservice.service.LookupService;

@RestController
@RequestMapping("/api/lookups")
public class LookupController {
	
	@Autowired
	LookupService lookupService;

    @GetMapping("/{type}")
    public List<LookupEntity> getByType(@PathVariable String type) {
       
    	return lookupService.getValues(type);
    }
}
