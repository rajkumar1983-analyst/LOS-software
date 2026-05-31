package com.banking.customerservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.customerservice.entity.CustomerEducation;

public interface CustomerEducationRepository extends JpaRepository<CustomerEducation,Integer> {

}
