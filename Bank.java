package com.ConsoleBankingApp;
import java.util.HashMap;

public class Bank {
	private HashMap<Integer, CreateAccount> customers = new HashMap<>();
	
	public void addCustomer(CreateAccount newAccount) {
		customers.put(newAccount.accountNumber, newAccount);
	}
	
	public void getBalance(int accountNumber) {
		CreateAccount balance = customers.get(accountNumber);
		System.out.println(balance.initialBalance);
	}
	public CreateAccount getCustomer(double withDrawAmount) {
		CreateAccount balance = customers.get(initialBalance);
		System.out.println(balance.customerName);
		return balance;
	}
	
}
