package com.banking.customerservice.repository;
import com.banking.customerservice.dto.projection.CustomerSummaryDTO;
import com.banking.customerservice.entity.Customer;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	boolean existsByFirstnameAndLastname(String firstname,String lastname);
	boolean existsByFirstnameAndLastnameAndKeycloakId(String firstname,String lastname,String keycloakId);
	List<CustomerSummaryDTO> findAllBy();
	Optional<Customer> findByKeycloakId(String keycloakId);
}

