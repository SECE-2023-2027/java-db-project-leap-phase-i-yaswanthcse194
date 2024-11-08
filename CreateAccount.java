package com.ConsoleBankingApp;

public class CreateAccount {
	int accountNumber;
	String customerName;
	double initialBalance;
	
	public CreateAccount(int accountNumber, String customerName, double initialBalance) {
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.initialBalance = initialBalance;
	}
	public int getAccountNumber() {
		return this.accountNumber;
	}
	public double getBalance() {
		return this.initialBalance;
	}
	public String getCoustomerName() {
		return this.customerName;
	}
	public void displayAccountDetails() {
		System.out.println("account created Successfully for :"+customerName);
	}
	public void creditToAccount(double creditAmount) {
		System.out.println(creditAmount);
		
	}
	public void WithdrawFromAccount(double withDrawAmount) {
		System.out.println(withDrawAmount);
		
	}
}
