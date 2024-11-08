package com.ConsoleBankingApp;
import java.util.*;

public class Main {
	public static void main(String args[]) {
		Bank bankobj = new Bank();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			System.out.println("\nBanking Operations: ");
			System.out.println("1. Create Account");
			System.out.println("2. credit to account");
			System.out.println("3. withdraw from account");
			System.out.print("Choose an operation: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
			case 1:
				System.out.print("Enter Account Number: ");
				int accountNumber = scanner.nextInt();
				System.out.print("Enter Account Holder Name: ");
				String accountHolderName=scanner.next();
				System.out.print("Enter Initial Balance: ");
				double balance=scanner.nextDouble();
				scanner.nextLine();
				System.out.print("Enter Account Type (Savings / Current): ");
				String accountType=scanner.next();
				
				CreateAccount createAccountObj=null;
				if(accountType.equalsIgnoreCase("Savings")) {
					System.out.print("Enter Transaction Limit: ");
					int transactionLimit=scanner.nextInt();
					scanner.nextLine();
					createAccountObj=new CreateSavingsAccount(accountNumber,accountHolderName,balance,transactionLimit);
				}
				else {
					createAccountObj=new CreateCurrentAccount(accountNumber,accountHolderName,balance);
				}
				
				bankobj.addCustomer(createAccountObj);
				createAccountObj.displayAccountDetails();
				break;
			case 2:
				System.out.print("Enter Account Number to Credit: ");
				int accNumDebit=scanner.nextInt();
				System.out.print("Enter Amount to Credit: ");
				double creditAmount=scanner.nextDouble();
				CreateAccount depositAccountobj=bankobj.getCustomer(accNumDebit);
				depositAccountobj.creditToAccount(creditAmount);
			case 3:
				System.out.println("Enter Account Number to Credit: ");
				int accNumDebit1=scanner.nextInt();
				System.out.print("Enter amount to withdraw: ");
				double withDrawAmount=scanner.nextDouble();
				CreateAccount Withdrawobj=bankobj.getCustomer(withDrawAmount);
				Withdrawobj.WithdrawFromAccount(withDrawAmount);
			}
		}
		scanner.close();
	}
}
