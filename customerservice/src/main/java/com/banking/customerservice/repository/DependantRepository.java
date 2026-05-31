package com.banking.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banking.customerservice.entity.*;

@Repository
public interface DependantRepository extends JpaRepository<Dependant, Long> {
	
}
