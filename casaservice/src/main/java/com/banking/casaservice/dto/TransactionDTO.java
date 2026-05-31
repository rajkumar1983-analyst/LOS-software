package com.banking.casaservice.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
	
	private int account_id;
	private int branch_id;
	private int transaction_type_id;
	private String debitorcredit;
	private float transaction_amt;
	private float new_balance;
	private float transfer_limit;	
	private int executed_by;
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
	public String getDebitorcredit() {
		return debitorcredit;
	}
	public void setDebitorcredit(String debitorcredit) {
		this.debitorcredit = debitorcredit;
	}
	public float getTransaction_amt() {
		return transaction_amt;
	}
	public void setTransaction_amt(float transaction_amt) {
		this.transaction_amt = transaction_amt;
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
	
	public int getExecuted_by() {
		return executed_by;
	}
	public void setExecuted_by(int executed_by) {
		this.executed_by = executed_by;
	}	
	
}
