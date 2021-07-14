package com.revature.services;

import java.util.List;

import com.revature.models.Login;
import com.revature.repos.LoginDAO;
import com.revature.repos.LoginDAOImpl;

public class LogInServices {
	
	private static LoginDAO loginDAO = new LoginDAOImpl();
	
	public Login findLogin(String username) {
		return loginDAO.findByUsername(username);
	}
	public boolean addLogin(Login login) {
		return loginDAO.addLogin(login);
	}
	public boolean removeLogin(String username) {
		return loginDAO.removeLogin(username);
	}
	public boolean approveLogin(Login login) {
		return loginDAO.approveLogin(login);
	}
	public List<Login> getAllLogin() {
		return loginDAO.findAll();
	}
	public Login checkUsername(String username) {
		return loginDAO.checkUsername(username);
	}

}
