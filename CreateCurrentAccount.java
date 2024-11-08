package com.ConsoleBankingApp;

public class CreateCurrentAccount extends CreateAccount{

	public CreateCurrentAccount(int accountNumber, String customerName, double initialBalance) {
		super(accountNumber, customerName, initialBalance);
	}
	@Override
	public void displayAccountDetails() {
		System.out.println("Type of Account created is Current");
	}
}

