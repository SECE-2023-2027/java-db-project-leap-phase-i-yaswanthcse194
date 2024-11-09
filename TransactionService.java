package com.sece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionService {

    public void performTransaction(int accountId, String transType) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Transaction Amount: ");
        int amount = scanner.nextInt();
        int currentBalance = getCurrentBalance(accountId);

        if (transType.equalsIgnoreCase("Debit") && currentBalance < amount) {
            System.out.println("Insufficient balance!");
            return;
        }

        int newBalance = transType.equalsIgnoreCase("Credit") ? currentBalance + amount : currentBalance - amount;

        String query = "INSERT INTO Transactions (account_id, date, trans_type, trans_amount, acc_balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnec.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, accountId);
            pstmt.setLong(2, System.currentTimeMillis());
            pstmt.setString(3, transType);
            pstmt.setInt(4, amount);
            pstmt.setInt(5, newBalance);
            pstmt.executeUpdate();
            System.out.println(transType + " transaction successful! New Balance: " + newBalance);
        }
    }

    // Get Current Balance
    private int getCurrentBalance(int accountId) throws SQLException {
        String query = "SELECT balance FROM Customers WHERE id = ?";
        try (Connection conn = DBConnec.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("balance") : 0;
        }
    }
}
