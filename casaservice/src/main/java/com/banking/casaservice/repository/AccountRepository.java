package com.banking.casaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.casaservice.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

}
