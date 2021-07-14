package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.BankAccount;
import com.revature.models.Login;
import com.revature.services.BankAccountServices;
import com.revature.services.LogInServices;

public class LogInController {
	
	private static LogInServices loginServices = new LogInServices();
	private static BankAccountServices bankAccountServices = new BankAccountServices();
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LogInController.class);

	public void logInMenu() {
		
		System.out.println("Please select an option: \n"
				+ "1) Create a new account \n"
				+ "2) Sign into your account \n"
				+ "3) Go back to previous menu");
		
		String response = scan.nextLine();
		
		switch(response) {
			case "1":
				addLogin();
				break;
			case "2":
				signIn();
				break;
			default:
				return;
		}
	}

	private void signIn() {
		System.out.println("Please type in your Username:");
		String username = scan.nextLine();
		
		System.out.println("Please type in your Password:");
		String userPass = scan.nextLine();
		
		Login login = loginServices.checkUsername(username);
		if(login.getApproved() == false) {
			System.out.println("You need to wait for you account to be approved.");
		}
		
		// If log in successful you go straight into the bank menu
		else if(username.equals(login.getUsername())) {
			if(userPass.equals(login.getUserPass())) {
				System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do today? \n"
						+ "1) Withdraw,deposit, or transfer from account \n"
						+ "2) Exit account");
				String response = scan.nextLine();
			
				while (!response.equals("2")) {
				
					
					// Changing our balance in some way	
					if (response.equals("1")) {
					
						BankAccount bankAccount = bankAccountServices.findBankAccount(username);
						if(username.equals(bankAccount.getAccountUser())) {
							System.out.println("You have " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
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
									System.out.println("You have " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
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
									System.out.println("You have " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
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
											bankAccount.setAccountBalance(currentBalance); 
											bankAccountServices.updateBankAccount(bankAccount);
										
											currentBalance = transferAccount.getAccountBalance() + amount;
											transferAccount.setAccountBalance(currentBalance); 
											bankAccountServices.updateBankAccount(transferAccount);
											log.info(bankAccount.getAccountUser() + " Just transfered " + amount + " Fake money's to " + transferAccount.getAccountUser());
											
										}
										System.out.println("You have " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
												+ "1) Withdraw \n"
												+ "2) Deposit \n"
												+ "3) Transfer \n"
												+ "4) Go back to previous menu");
										reply = scan.nextLine();
										
									}else {
										System.out.println("We can't find a user with that name");
									}
								
								}else {
									System.out.println("Invalid choice");
									System.out.println("You have " + bankAccount.getAccountBalance() + " fake money's. Would you like to: \n"
											+ "1) Withdraw \n"
											+ "2) Deposit \n"
											+ "3) Transfer \n"
											+ "4) Go back to previous menu");
									reply = scan.nextLine();
								}
							}
						
						}else {
						System.out.println("Invalid choice");
						}
					
						System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do today? \n"
							+ "1) Withdraw, deposit, or transfer from account \n"
							+ "2) Exit account");
						response = scan.nextLine();
					
					// invalid response	
					}else {
						System.out.println("That is not a valid input. Try again.");
						System.out.println("Welcome " + login.getFirstName() + " " + login.getLastName() + "! What would you like to do today? \n"
							+ "1) Withdraw, deposit, or transfer from account \n"
							+ "2) Exit account ");
						response = scan.nextLine();
					}
				}
			}
			//If log in is unsuccessful
		}else {
			System.out.println("Invalid Username or Password.");
		}
		
	}

	private void addLogin() {
		System.out.println("What would you like your Username to be? (20 character limit)");
		String username = scan.nextLine();
			
		if(username.length()>20) {
			System.out.println("Username exceeds 20 characters, try again.");
			username = scan.nextLine();
		}
		Login login = loginServices.checkUsername(username);
		while(username.equals(login.getUsername())) {
			System.out.println("That username already exists");
			username = scan.nextLine();
			login = loginServices.checkUsername(username);
			if(username.length()>20) {
				System.out.println("Username exceeds 20 characters, try again.");
				username = scan.nextLine();
			}
		}
		
		System.out.println("What would you like your password to be? (20 character limit)");
		String userPass = scan.nextLine();
		
		while(userPass.length()>20) {
			System.out.println("Password exceeds 20 characters, try again.");
			userPass = scan.nextLine();
		}
		
		System.out.println("What is your first name? (20 character limit)");
		String firstName = scan.nextLine();
		
		while(firstName.length()>20) {
			System.out.println("Im sorry, but your first name is to long. Please shorten it.");
			firstName = scan.nextLine();
		}
					
		System.out.println("What is your last name? (20 character limit)");
		String lastName = scan.nextLine();
		
		while(lastName.length()>20) {
			System.out.println("Im sorry, but your last name is to long. Please shorten it.");
			lastName = scan.nextLine();
		}
		
		login = new Login(username, userPass, firstName, lastName, "user", false);
		
		String accountUser = username;
		String accountInfo = "";
		while(accountInfo.equals("")) {
				System.out.println("What bank account would you like? \n"
						+ "1) Savings \n"
						+ "2) Checking ");
				String response = scan.nextLine();
				if(response.equals("1")) {
					accountInfo = "Savings";
				}else if(response.equals("2")) {
					accountInfo = "Checking";
				}else {
					System.out.println("Invalid command. Try again");
				}
			}
				
		if(loginServices.addLogin(login)) {
			System.out.println("Your account was created. You must wait for your account to get approved before you can proceed with logging in.");
			BankAccount bankAccount = new BankAccount(0, accountUser, accountInfo, 0);
			bankAccountServices.addBankAccount(bankAccount);
		}else {
			System.out.println("Something went wrong, you can try again.");
		}
	}
	

}
