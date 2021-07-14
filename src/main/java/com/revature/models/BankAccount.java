package com.revature.models;

public class BankAccount {
	
	private int accountNumber;
	private String accountUser;
	private String accountInfo;
	private double accountBalance;
	
	public BankAccount(int accountNumber, String accountUser, String accountInfo, double accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountUser = accountUser;
		this.accountInfo = accountInfo;
		this.accountBalance = accountBalance;
	}

	public BankAccount() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountInfo == null) ? 0 : accountInfo.hashCode());
		result = prime * result + accountNumber;
		result = prime * result + ((accountUser == null) ? 0 : accountUser.hashCode());
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountInfo == null) {
			if (other.accountInfo != null)
				return false;
		} else if (!accountInfo.equals(other.accountInfo))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountUser == null) {
			if (other.accountUser != null)
				return false;
		} else if (!accountUser.equals(other.accountUser))
			return false;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		return true;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountUser=" + accountUser + ", accountInfo="
				+ accountInfo + ", account_balance=" + accountBalance + "]";
	}
	
	
	
	

}
