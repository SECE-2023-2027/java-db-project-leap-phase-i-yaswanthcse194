package com.sece;
import java.util.Scanner;


import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		BankingServices accountService = new BankingServices();
		TransactionService transactionService = new TransactionService();
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
            System.out.println("\n1. Admin Login\n2. Customer Login\n3. Logout\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
			    case 1 -> {
			        System.out.print("Enter Admin Username: ");
			        String adminUser = scanner.nextLine();
			        System.out.print("Enter Admin Password: ");
			        String adminPass = scanner.nextLine();
			        if (accountService.adminLogin(adminUser, adminPass)) {
			            System.out.println("Admin login successful!");
			            adminActions(accountService, transactionService);
			        } else {
			            System.out.println("Invalid admin credentials.");
			        }
			    }
			}
        }
		
	}
	
	private static void adminActions(BankingServices accountService, TransactionService transactionService) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (accountService.isAdminLoggedIn()) {
            System.out.println("\n1. Create Account\n2. Credit Transaction\n3. Debit Transaction\n4. Logout");
            System.out.print("Choose an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1 -> accountService.createCustomer();
                case 2 -> {
                    System.out.print("Enter Account ID to Credit: ");
                    int accId = scanner.nextInt();
                    transactionService.performTransaction(accId, "Credit");
                }
            }
        }
    }

}
