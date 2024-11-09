package com.sece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankingServices {
	private Connection connection;
	boolean adminLoggedIn = false;
	
	 public BankingServices() {
	        try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3002/banking_db", "root", "root123");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	public boolean adminLogin(String userName, String password) {
        String query = "SELECT * FROM Admin WHERE user_name = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Admin logged in successfully!");
                adminLoggedIn = true;  // Set admin logged in status to true
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Invalid admin credentials.");
        return false;
    }
	
	public void createCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Account Type (Savings/Current): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        int balance = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        String insertQuery = "INSERT INTO Customers (user_name, password, balance, account_type, email, phone, address, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'active')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, balance);
            preparedStatement.setString(4, accountType);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, address);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated account ID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int accountId = generatedKeys.getInt(1);
                    System.out.println("Customer account created successfully!");
                    System.out.println("Account Number: " + accountId); // Print the account number
                }
            } else {
                System.out.println("Account creation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void performTransaction(int accountId, String transType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter transaction amount: ");
        int amount = scanner.nextInt();

        String query = "UPDATE Customers SET balance = balance " + (transType.equals("Credit") ? "+" : "-") + " ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, accountId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transaction successful! " + transType + " of " + amount + " completed.");
            } else {
                System.out.println("Transaction failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public boolean isAdminLoggedIn() {
        return adminLoggedIn; 
    }
}
