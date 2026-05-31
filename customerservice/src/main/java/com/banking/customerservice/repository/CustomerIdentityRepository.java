package com.banking.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.customerservice.entity.CustomerIdentity;

public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity,Integer> {

}
