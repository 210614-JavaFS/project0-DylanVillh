package com.revature.repos;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDAO {
	
	public List<BankAccount> findAll();
	public BankAccount findBankAccount(String username);
	public boolean addBankAccount(BankAccount bankAccount);
	public boolean checkBankAccount(String username);
	public boolean updateBankAccount(BankAccount bankAccount);
	public boolean removeBankAccount(String username);
}
