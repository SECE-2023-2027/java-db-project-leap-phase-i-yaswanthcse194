package com.ConsoleBankingApp;

public class CreateSavingsAccount extends CreateAccount{
	private int transactionLimit;
	public CreateSavingsAccount(int accountNumber,String customerName,double initialBalance, int transactionLimit) {
		super(accountNumber,customerName,initialBalance);
		this.transactionLimit = transactionLimit;
	}
	public int getTransactionList() {
		return this.transactionLimit;
	}
	@Override
	public void displayAccountDetails() {
		super.displayAccountDetails();
		System.out.println("Type of Account created is Savings");
	}
}
