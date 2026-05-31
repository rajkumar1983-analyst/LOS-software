package com.banking.casaservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.casaservice.dto.AccountDTO;
import com.banking.casaservice.entity.AccountEntity;
import com.banking.casaservice.repository.AccountRepository;

@Service
public class AccountService {
	
	AccountEntity accentity;
	
	@Autowired
	AccountRepository accRepo;
	
	public AccountDTO createAccount(AccountDTO dto)
	{
		accentity = new AccountEntity();
		accentity.setBranch_id(dto.getBranch_id());
		accentity.setProduct_id(dto.getProduct_id());
		accentity.setCustomer_id(dto.getCustomer_id());
		accentity.setProduct_sub_typ_id(dto.getProduct_sub_typ_id());
		accentity.setClearing_limit(dto.getClearing_limit());
		accentity.setTransfer_limit(dto.getTransfer_limit());
		accentity.setWithdrawal_limit(dto.getWithdrawal_limit());
		accentity.setCard_available(dto.isCard_available());
		accentity.setCheque_available(dto.isCheque_available());		
		accRepo.save(accentity);
		return dto;
	}
	
	public Optional<AccountDTO> getAccountbyID(int id) {		
		AccountEntity accEntity = accRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
		
		AccountDTO dto = new AccountDTO();
		dto.setBranch_id(accEntity.getBranch_id());
		dto.setProduct_id(accEntity.getProduct_id());
		dto.setProduct_sub_typ_id(accEntity.getProduct_sub_typ_id());
		dto.setTransfer_limit(accEntity.getTransfer_limit());
		dto.setWithdrawal_limit(accEntity.getWithdrawal_limit());
		dto.setClearing_limit(accEntity.getClearing_limit());
		dto.setCard_available(accEntity.isCard_available());
		dto.setCheque_available(accEntity.isCheque_available());
		return Optional.ofNullable(dto);
	}
	
	public List<AccountDTO> getAllAccounts()
	{
		List<AccountEntity> entities = accRepo.findAll();
		List<AccountDTO> dtoList = new ArrayList();
		
		for (AccountEntity entity:entities) {
			AccountDTO dto = new AccountDTO();
			dto.setBranch_id(entity.getBranch_id());
			dto.setProduct_id(entity.getProduct_id());
			dto.setProduct_sub_typ_id(entity.getProduct_sub_typ_id());
			dto.setTransfer_limit(entity.getTransfer_limit());
			dto.setWithdrawal_limit(entity.getWithdrawal_limit());
			dto.setClearing_limit(entity.getClearing_limit());
			dto.setCard_available(entity.isCard_available());
			dto.setCheque_available(entity.isCheque_available());
		}
		return dtoList;
	}
	
}