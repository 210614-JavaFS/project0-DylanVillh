package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.BankAccount;
import com.revature.utils.ConnectionUtil;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	private static Logger log = LoggerFactory.getLogger(BankAccountDAOImpl.class);

	@Override
	public BankAccount findBankAccount(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_account WHERE account_user = ?;";
			
			log.debug(sql);
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//This is where SQL injection is checked for.
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			BankAccount bankAccount = new BankAccount();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				bankAccount.setAccountNumber(result.getInt("account_number"));
				bankAccount.setAccountUser(result.getString("account_user"));
				bankAccount.setAccountInfo(result.getString("account_info"));
				bankAccount.setAccountBalance(result.getDouble("account_balance"));
			}
			
			return bankAccount;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addBankAccount(BankAccount bankAccount) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_account (account_user, account_info)"
					+ " VALUES (?,?);";
			
			log.debug(sql);
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, bankAccount.getAccountUser());
			statement.setString(++index, bankAccount.getAccountInfo());
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkBankAccount(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_account";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<BankAccount> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setAccountNumber(result.getInt("account_number"));
				bankAccount.setAccountUser(result.getString("account_user"));
				bankAccount.setAccountInfo(result.getString("account_info"));
				bankAccount.setAccountBalance(result.getDouble("account_balance"));
				list.add(bankAccount);
			}
			
			for (int i = 0 ; i < list.size(); i++) {
				String compare = list.get(i).getAccountUser();
				if(compare.equals(username)) {
					return true;
				} else {
					return false;
				}
			}
					
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBankAccount(BankAccount bankAccount) {
		try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "UPDATE bank_account SET account_balance = ? WHERE account_user = ?;";
				
				log.debug(sql);
				
				PreparedStatement statement = conn.prepareStatement(sql);
				
				
				statement.setDouble(1, bankAccount.getAccountBalance());
				statement.setString(2, bankAccount.getAccountUser());

				
				statement.executeUpdate();
				
				return true;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

	@Override
	public List<BankAccount> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_account";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<BankAccount> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setAccountNumber(result.getInt("account_number"));
				bankAccount.setAccountUser(result.getString("account_user"));
				bankAccount.setAccountInfo(result.getString("account_info"));
				bankAccount.setAccountBalance(result.getDouble("account_balance"));
				list.add(bankAccount);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeBankAccount(String username) {
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "DELETE FROM bank_account WHERE account_user = ?;";
				
				log.debug(sql);
				
				PreparedStatement statement = conn.prepareStatement(sql);
				
				//This is where SQL injection is checked for.
				statement.setString(1, username);
				
				statement.execute();
				
				return true;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

