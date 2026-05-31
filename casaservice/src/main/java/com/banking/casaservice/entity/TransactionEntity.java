package com.banking.casaservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="[transaction]", schema = "dbo")
public class TransactionEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	private int account_id;
	private int branch_id;
	private int transaction_type_id;
	private String Credit_or_debit;
	private float transaction_amount;
	private float new_balance;
	private float transfer_limit;
	
	private LocalDateTime executed_on;
	@PrePersist
    public void prePersist() {
        this.executed_on = LocalDateTime.now();
    }
	private int executed_by;
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getTransaction_type_id() {
		return transaction_type_id;
	}
	public void setTransaction_type_id(int transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}
	public String getCredit_or_debit() {
		return Credit_or_debit;
	}
	public void setCredit_or_debit(String credit_or_debit) {
		Credit_or_debit = credit_or_debit;
	}
	public float getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amt(float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public float getNew_balance() {
		return new_balance;
	}
	public void setNew_balance(float new_balance) {
		this.new_balance = new_balance;
	}
	public float getTransfer_limit() {
		return transfer_limit;
	}
	public void setTransfer_limit(float transfer_limit) {
		this.transfer_limit = transfer_limit;
	}
	public LocalDateTime getExecuted_on() {
		return executed_on;
	}
	public void setExecuted_on(LocalDateTime executed_on) {
		this.executed_on = executed_on;
	}
	public int getExecuted_by() {
		return executed_by;
	}
	public void setExecuted_by(int executed_by) {
		this.executed_by = executed_by;
	}	
	
}
