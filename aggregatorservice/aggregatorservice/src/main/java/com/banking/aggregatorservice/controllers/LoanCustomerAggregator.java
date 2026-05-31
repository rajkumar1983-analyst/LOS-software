package com.banking.aggregatorservice.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.aggregatorservice.dto.LoanCustomerDTO;
import com.banking.aggregatorservice.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class LoanCustomerAggregator {

	private final DashboardService dashboardService;

    public LoanCustomerAggregator(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/loans")
    public List<LoanCustomerDTO> getLoansWithCustomers() {
        return dashboardService.getLoansWithCustomers();
    }
	
}
