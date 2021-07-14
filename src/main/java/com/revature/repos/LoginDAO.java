package com.revature.repos;

import java.util.List;

import com.revature.models.Login;

public interface LoginDAO {
	
	public List<Login> findAll();
	public Login findByUsername(String username);
	public boolean addLogin(Login login);
	public boolean removeLogin(String username);
	public boolean approveLogin(Login login);
	public Login checkUsername(String username);
}
