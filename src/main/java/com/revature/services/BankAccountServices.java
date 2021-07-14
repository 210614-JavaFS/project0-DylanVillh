package com.revature.services;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.repos.BankAccountDAO;
import com.revature.repos.BankAccountDAOImpl;

public class BankAccountServices {
	
	private static BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	
	public BankAccount findBankAccount(String username) {
		return bankAccountDAO.findBankAccount(username);
	}
	public boolean addBankAccount(BankAccount bankAccount) {
		return bankAccountDAO.addBankAccount(bankAccount);
	}
	public boolean checkBankAccount(String username) {
		return bankAccountDAO.checkBankAccount(username);
	}
	public boolean updateBankAccount(BankAccount bankAccount) {
		return bankAccountDAO.updateBankAccount(bankAccount);
		
	}
	public List<BankAccount> getAllBankAccounts() {
		return bankAccountDAO.findAll();
	}
	public boolean removeBankAccount(String username) {
		return bankAccountDAO.removeBankAccount(username);
	}

}
