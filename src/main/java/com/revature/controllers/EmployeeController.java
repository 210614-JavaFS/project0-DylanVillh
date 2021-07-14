package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.BankAccount;
import com.revature.models.Login;
import com.revature.services.BankAccountServices;
import com.revature.services.LogInServices;

public class EmployeeController {
	
	private static LogInServices loginServices = new LogInServices();
	private static BankAccountServices bankAccountServices = new BankAccountServices();
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

	public void employeeMenu() {
		
		System.out.println("Please type in your Username:");
		String username = scan.nextLine();
			
		System.out.println("Please type in your Password:");
		String userPass = scan.nextLine();
			
		Login login = loginServices.checkUsername(username);
		// If log in successful you go straight into the employee menu
		if(username.equals(login.getUsername())) {
				
			if(userPass.equals(login.getUserPass())) {
				
				if(login.getEmployee().equals("employee")){
					
					System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
							+ "1) Approve accounts. \n"
							+ "2) Look up Bank Account Info \n"
							+ "3) Look up User Account Info \n"
							+ "4) Sign out.");
					String answer = scan.nextLine();
					
					while(!answer.equals("4")) {
						
						if(answer.equals("1")) {
							System.out.println("Here is a list of all unapproved applications:");
							
							
							List<Login> loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								if(l.getApproved() == false) {
									System.out.println("---------------------------------------------");
									System.out.println(l.getUsername());
									System.out.println("---------------------------------------------");
								}
							}
							System.out.println("Would you like to approve any accounts? (yes/no)");
							String response = scan.nextLine();
							while(response.equals("yes")) {
								
								if(response.equals("no")) {
									System.out.println("signing out.");
								}else if(response.equals("yes")) {
									System.out.println("Type the Username of you you would like to approve:");
									username = scan.nextLine();
									Login user = loginServices.checkUsername(username);	
									user.setApproved(true);
									loginServices.approveLogin(user);
									if(loginServices.approveLogin(login)) {
										System.out.println("Account approved");
									}
								}else {
									System.out.println("invalid response");
								}
								System.out.println("Would you like to approve any accounts? (yes/no)");
								response = scan.nextLine();
							}
							System.out.println("Returning to main menu.");
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Look up Bank Account Info \n"
									+ "3) Look up User Account Info \n"
									+ "4) Sign out.");
							answer = scan.nextLine();
						}
						
						else if(answer.equals("2")){
							
							System.out.println("Here is a list of all Bank Accounts:");
							
							
							List<BankAccount> bankAccountList = bankAccountServices.getAllBankAccounts();
							for(BankAccount b:bankAccountList) {
								System.out.println("---------------------------------------------");
									System.out.println("Account Number: " + b.getAccountNumber());
									System.out.println("Account User: " + b.getAccountUser());
									System.out.println("Account Info: " + b.getAccountInfo());
									System.out.println("Account Balance: " + b.getAccountBalance() + " Fake Money's");
									System.out.println("---------------------------------------------");
							
							}
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Look up Bank Account Info \n"
									+ "3) Look up User Account Info \n"
									+ "4) Sign out.");
							answer = scan.nextLine();
							
						}else if(answer.equals("3")) {
							
							System.out.println("Here is a list of all User Accounts:");
							
							
							List<Login> loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								System.out.println("---------------------------------------------");
									System.out.println("Username: " + l.getUsername());
									System.out.println("Password: " + l.getUserPass());
									System.out.println("First Name: " + l.getFirstName());
									System.out.println("Last Name: " + l.getLastName());
									System.out.println("Level of Access: " + l.getEmployee());
									System.out.println("Are they Approved: " + l.getApproved());
									System.out.println("---------------------------------------------");
							}
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Look up Bank Account Info \n"
									+ "3) Look up User Account Info \n"
									+ "4) Sign out.");
							answer = scan.nextLine();
						}else {
							System.out.println("Invalid choice");
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Look up Bank Account Info \n"
									+ "3) Look up User Account Info \n"
									+ "4) Sign out.");
							answer = scan.nextLine();
						}
						
						
					}
					
					
					
					
				}else if(login.getEmployee().equals("admin")) {
					System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
							+ "1) Approve accounts. \n"
							+ "2) Withdraw, deposit, or transfer money from accounts. \n"
							+ "3) Cancel an account. \n"
							+ "4) Look up Bank Accounts \n"
							+ "5) Look up User Accounts \n"
							+ "6) Sign out.");
					String response = scan.nextLine();
					
					
					while (!response.equals("6")){
						
						if(response.equals("1")) {
							
							System.out.println("Here is a list of all unapproved applications:");
							
							List<Login> loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								if(l.getApproved() == false) {
									System.out.println(l.getUsername());
									System.out.println("");
								}
							}
							System.out.println("Would you like to approve any accounts? (yes/no)");
							response = scan.nextLine();
								
								if(response.equals("no")) {
									System.out.println("signing out.");
								}else if(response.equals("yes")) {
									System.out.println("Type the Username of you you would like to approve:");
									username = scan.nextLine();
									Login user = loginServices.checkUsername(username);	
									user.setApproved(true);
									loginServices.approveLogin(user);
									if(loginServices.approveLogin(login)) {
										System.out.println("Account approved");
									}
								}else {
									System.out.println("invalid response");
								}
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Withdraw, deposit, or transfer money from accounts. \n"
									+ "3) Cancel an account. \n"
									+ "4) Look up Bank Accounts \n"
									+ "5) Look up User Accounts \n"
									+ "6) Sign out.");
							response = scan.nextLine();
							
						}else if(response.equals("2")) {
							
							List<Login>loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								System.out.println(l.getUsername());
							}
							System.out.println("Which account would you like to select from above?");
							username = scan.nextLine(); 
								
							BankAccount bankAccount = bankAccountServices.findBankAccount(username);
							if(username.equals(bankAccount.getAccountUser())) {
								System.out.println(bankAccount.getAccountUser() + " has " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
										+ "1) Withdraw \n"
										+ "2) Deposit \n"
										+ "3) Transfer \n"
										+ "4) Go back to previous menu");
								String reply = scan.nextLine();
								
								double amount = 0;
								while(!reply.equals("4")) {
									
									if(reply.equals("1")) {
										System.out.println("How much would you like to withdraw?");
										amount = scan.nextDouble();
										scan.nextLine();
										if(amount > bankAccount.getAccountBalance()) {
											System.out.println("You don't have enough money!");
										} else if(amount < 0) {
											System.out.println("You need to put in a positive amount.");
										}else {
											double currentBalance = bankAccount.getAccountBalance() - amount;
											bankAccount.setAccountBalance(currentBalance); 
											bankAccountServices.updateBankAccount(bankAccount);
											log.info(bankAccount.getAccountUser() + " Just withdrew " + amount + " Fake money's");
											
											
										}
										System.out.println(bankAccount.getAccountUser() + " has " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
												+ "1) Withdraw \n"
												+ "2) Deposit \n"
												+ "3) Transfer \n"
												+ "4) Go back to previous menu");
										reply = scan.nextLine();
										
									}else if(reply.equals("2")) {
										System.out.println("How much would you like to Deposit?");
										amount = scan.nextDouble();
										scan.nextLine();
										if(amount < 0) {
											System.out.println("You need to put in a positive amount.");
										}else {
											double currentBalance = bankAccount.getAccountBalance() + amount;
											bankAccount.setAccountBalance(currentBalance); 
											bankAccountServices.updateBankAccount(bankAccount);
											log.info(bankAccount.getAccountUser() + " Just deposited " + amount + " Fake money's");
											
										}
										System.out.println(bankAccount.getAccountUser() + " has " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
												+ "1) Withdraw \n"
												+ "2) Deposit \n"
												+ "3) Transfer \n"
												+ "4) Go back to previous menu");
										reply = scan.nextLine();
									
									
									}else if(reply.equals("3")) {
										System.out.println("Who do you want to transfer to?");
										String otherUser = scan.nextLine();
											
										BankAccount transferAccount = bankAccountServices.findBankAccount(otherUser);
										//if there is another account
										if(otherUser.equals(transferAccount.getAccountUser())) {
											
											System.out.println("How much would you like to transfer?");
											amount = scan.nextDouble();
											scan.nextLine();
											if(amount > bankAccount.getAccountBalance()) {
												System.out.println("You don't have enough money!");
											} else if(amount < 0) {
												System.out.println("You need to put in a positive amount.");
											}else {
												double currentBalance = bankAccount.getAccountBalance() - amount;
												double roundDbl = Math.round(currentBalance*100.0)/100.0;
												bankAccount.setAccountBalance(roundDbl); 
												bankAccountServices.updateBankAccount(bankAccount);
												
												currentBalance = transferAccount.getAccountBalance() + amount;
												roundDbl = Math.round(currentBalance*100.0)/100.0;
												transferAccount.setAccountBalance(roundDbl); 
												bankAccountServices.updateBankAccount(transferAccount);
												log.info(bankAccount.getAccountUser() + " Just transfered " + amount + " Fake money's to " + transferAccount.getAccountUser());
												
											}
										}else {
											System.out.println("We can't find a user with that name");
										}
											
											System.out.println(bankAccount.getAccountUser() + " has " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
													+ "1) Withdraw \n"
													+ "2) Deposit \n"
													+ "3) Transfer \n"
													+ "4) Go back to previous menu");
											reply = scan.nextLine();	
										
									}else {
										System.out.println("Invalid choice");
										System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
												+ "1) Approve accounts. \n"
												+ "2) Withdraw, deposit, or transfer money from accounts. \n"
												+ "3) Cancel an account. \n"
												+ "4) Look up Bank Accounts \n"
												+ "5) Look up User Accounts \n"
												+ "6) Sign out.");
										response = scan.nextLine();
									}
								}
								System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
										+ "1) Approve accounts. \n"
										+ "2) Withdraw, deposit, or transfer money from accounts. \n"
										+ "3) Cancel an account. \n"
										+ "4) Look up Bank Accounts \n"
										+ "5) Look up User Accounts \n"
										+ "6) Sign out.");
								response = scan.nextLine();
								
							}
							
						}else if(response.equals("3")){
							
							List<Login> loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								System.out.println(l.getUsername());
							}
							System.out.println("Which account would you like to select from above?");
							username = scan.nextLine(); 
							
							loginServices.removeLogin(username);
							bankAccountServices.removeBankAccount(username);
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Withdraw, deposit, or transfer money from accounts. \n"
									+ "3) Cancel an account. \n"
									+ "4) Look up Bank Accounts \n"
									+ "5) Look up User Accounts \n"
									+ "6) Sign out.");
							response = scan.nextLine();
							
						}else if(response.equals("4")) {
							System.out.println("Here is a list of all Bank Accounts:");
							
							
							List<BankAccount> bankAccountList = bankAccountServices.getAllBankAccounts();
							for(BankAccount b:bankAccountList) {
								System.out.println("---------------------------------------------");
									System.out.println("Account Number: " + b.getAccountNumber());
									System.out.println("Account User: " + b.getAccountUser());
									System.out.println("Account Info: " + b.getAccountInfo());
									System.out.println("Account Balance: " + b.getAccountBalance() + " Fake Money's");
									System.out.println("---------------------------------------------");
							
							}
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Withdraw, deposit, or transfer money from accounts. \n"
									+ "3) Cancel an account. \n"
									+ "4) Look up Bank Accounts \n"
									+ "5) Look up User Accounts \n"
									+ "6) Sign out.");
							response = scan.nextLine();
							
						}else if(response.equals("5")) {
							System.out.println("Here is a list of all User Accounts:");
							
							
							List<Login> loginList = loginServices.getAllLogin();
							for(Login l:loginList) {
								System.out.println("---------------------------------------------");
									System.out.println("Username: " + l.getUsername());
									System.out.println("Password: " + l.getUserPass());
									System.out.println("First Name: " + l.getFirstName());
									System.out.println("Last Name: " + l.getLastName());
									System.out.println("Level of Access: " + l.getEmployee());
									System.out.println("Are they Approved: " + l.getApproved());
									System.out.println("---------------------------------------------");
							}
							
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Withdraw, deposit, or transfer money from accounts. \n"
									+ "3) Cancel an account. \n"
									+ "4) Look up Bank Accounts \n"
									+ "5) Look up User Accounts \n"
									+ "6) Sign out.");
							response= scan.nextLine();
						
						
						}else {
							System.out.println("Invalid response");
							System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do? \n"
									+ "1) Approve accounts. \n"
									+ "2) Withdraw, deposit, or transfer money from accounts. \n"
									+ "3) Cancel an account. \n"
									+ "4) Look up Bank Accounts \n"
									+ "5) Look up User Accounts \n"
									+ "6) Sign out.");
							response = scan.nextLine();
						}
					}
					
				}else {
					System.out.println("You are not an employee here.");
				}
			}
		}
	}
}