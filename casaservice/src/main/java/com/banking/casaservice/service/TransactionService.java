package com.banking.casaservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.casaservice.dto.AccountDTO;
import com.banking.casaservice.dto.TransactionDTO;
import com.banking.casaservice.entity.AccountEntity;
import com.banking.casaservice.entity.TransactionEntity;
import com.banking.casaservice.repository.TransactionRepository;

@Service
public class TransactionService {
	
	TransactionEntity tranEntity;
	
	@Autowired
	TransactionRepository tranRepository;
	
	public TransactionDTO createTransaction(TransactionDTO request)
	{
		tranEntity = new TransactionEntity();
		tranEntity.setAccount_id(request.getAccount_id());
		tranEntity.setBranch_id(request.getBranch_id());
		tranEntity.setTransaction_type_id(request.getTransaction_type_id());
		tranEntity.setCredit_or_debit(request.getDebitorcredit());
		tranEntity.setTransaction_amt(request.getTransaction_amt());
		tranEntity.setTransfer_limit(request.getTransfer_limit());
		tranEntity.setNew_balance(request.getNew_balance());
		tranEntity.setExecuted_by(request.getExecuted_by());
		tranRepository.save(tranEntity);
		return request;
	}
	
	public Optional<TransactionDTO> getTransactionbyID(int id) {		
		tranEntity = tranRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
		
		TransactionDTO dto = new TransactionDTO();
		dto.setAccount_id(tranEntity.getAccount_id());
		dto.setBranch_id(tranEntity.getBranch_id());
		dto.setTransaction_type_id(tranEntity.getTransaction_type_id());
		dto.setTransaction_amt(tranEntity.getTransaction_amount());
		dto.setNew_balance(tranEntity.getNew_balance());
		dto.setDebitorcredit(tranEntity.getCredit_or_debit());		
		return Optional.ofNullable(dto);
	}
	
	public List<TransactionDTO> getAllTransactions()
	{
		List<TransactionEntity> entities = tranRepository.findAll();
		List<TransactionDTO> dtoList = new ArrayList();
		
		for (TransactionEntity entity:entities) {			
			TransactionDTO dto = new TransactionDTO();
			dto.setAccount_id(tranEntity.getAccount_id());
			dto.setBranch_id(tranEntity.getBranch_id());
			dto.setTransaction_type_id(tranEntity.getTransaction_type_id());
			dto.setTransaction_amt(tranEntity.getTransaction_amount());
			dto.setNew_balance(tranEntity.getNew_balance());
			dto.setDebitorcredit(tranEntity.getCredit_or_debit());
		}
		return dtoList;
	}


}
