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

import com.revature.models.Login;
import com.revature.utils.ConnectionUtil;

public class LoginDAOImpl implements LoginDAO{
	
	private static Logger log = LoggerFactory.getLogger(LoginDAOImpl.class);


	@Override
	public Login findByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM login WHERE username = ?;";
			
			log.debug(sql);
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//This is where SQL injection is checked for.
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			Login login = new Login();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				login.setUsername(result.getString("username"));
				login.setUserPass(result.getString("user_pass"));
				login.setFirstName(result.getString("first_name"));
				login.setLastName(result.getString("last_name"));
				login.setEmployee(result.getString("employee"));
				login.setApproved(result.getBoolean("approved"));
			}
			
			return login;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addLogin(Login login) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO login (username, user_pass, first_name, last_name, employee, approved) VALUES (?,?,?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, login.getUsername());
			statement.setString(++index, login.getUserPass());
			statement.setString(++index, login.getFirstName());
			statement.setString(++index, login.getLastName());
			statement.setString(++index, login.getEmployee());
			statement.setBoolean(++index, login.getApproved());
			
			log.debug(sql);
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeLogin(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM login WHERE username = ?;";
			
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

	@Override
	public boolean approveLogin(Login login) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE login SET approved = ? WHERE username = ?;";
			
			log.debug(sql);
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			
			statement.setBoolean(1, login.getApproved());
			statement.setString(2, login.getUsername());
			
			statement.executeUpdate();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Login> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM login";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Login> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Login login = new Login();
				login.setUsername(result.getString("username"));
				login.setUserPass(result.getString("user_pass"));
				login.setFirstName(result.getString("first_name"));
				login.setLastName(result.getString("last_name"));
				login.setEmployee(result.getString("employee"));
				login.setApproved(result.getBoolean("approved"));
				list.add(login);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Login checkUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM login WHERE username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			Login login = new Login();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				login.setUsername(result.getString("username"));
				login.setUserPass(result.getString("user_pass"));
				login.setFirstName(result.getString("first_name"));
				login.setLastName(result.getString("last_name"));
				login.setEmployee(result.getString("employee"));
				login.setApproved(result.getBoolean("approved"));
			}
			
			return login;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
