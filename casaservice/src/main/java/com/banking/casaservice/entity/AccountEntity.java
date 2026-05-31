package com.banking.casaservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="[account]", schema = "dbo")
public class AccountEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_id;
	private int product_id;	
	private int product_sub_typ_id;
	private int customer_id;
	private int branch_id;
	private float withdrawal_limit;
	private float clearing_limit;
	private float transfer_limit;
	private boolean card_available;
	private boolean cheque_available;
	private LocalDateTime created_on;
	@PrePersist
    public void prePersist() {
        this.created_on = LocalDateTime.now();
    }
	
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_sub_typ_id() {
		return product_sub_typ_id;
	}
	public void setProduct_sub_typ_id(int product_sub_typ_id) {
		this.product_sub_typ_id = product_sub_typ_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public float getWithdrawal_limit() {
		return withdrawal_limit;
	}
	public void setWithdrawal_limit(float withdrawal_limit) {
		this.withdrawal_limit = withdrawal_limit;
	}
	public float getClearing_limit() {
		return clearing_limit;
	}
	public void setClearing_limit(float clearing_limit) {
		this.clearing_limit = clearing_limit;
	}
	public float getTransfer_limit() {
		return transfer_limit;
	}
	public void setTransfer_limit(float transfer_limit) {
		this.transfer_limit = transfer_limit;
	}
	public boolean isCard_available() {
		return card_available;
	}
	public void setCard_available(boolean card_available) {
		this.card_available = card_available;
	}
	public boolean isCheque_available() {
		return cheque_available;
	}
	public void setCheque_available(boolean cheque_available) {
		this.cheque_available = cheque_available;
	}
	public LocalDateTime getCreated_on() {
		return created_on;
	}
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}

}
