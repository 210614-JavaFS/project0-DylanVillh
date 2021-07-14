package com.revature;

import java.util.Scanner;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.LogInController;

public class Driver {
	
	private static LogInController logInController = new LogInController();
	private static EmployeeController employeeController = new EmployeeController();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Welcome to Dylan's banking app!");
		System.out.println("What would you like to do? \n"
				+ "1) User Log in or Sign Up \n"
				+ "2) Employee log in \n"
				+ "3) exit the banking app");
		String response = scan.nextLine();
		
		while (!response.equals("3")){
			if(response.equals("1")) {
				logInController.logInMenu();
				System.out.println("What would you like to do? \n"
						+ "1) User Log in or Sign Up \n"
						+ "2) Employee log in \n"
						+ "3) exit the banking app");
				response = scan.nextLine();
			}else if(response.equals("2")) {
				employeeController.employeeMenu();
				System.out.println("What would you like to do? \n"
						+ "1) User Log in or Sign Up \n"
						+ "2) Employee log in \n"
						+ "3) exit the banking app");
				response = scan.nextLine();
			}else {
				System.out.println("That is not a valid input. Try again.");
				System.out.println("What would you like to do? \n"
						+ "1) User Log in or Sign Up \n"
						+ "2) Employee log in \n"
						+ "3) exit the banking app");
				response = scan.nextLine();
			}
		}



	}

}
