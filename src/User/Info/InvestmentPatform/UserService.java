package User.Info.InvestmentPatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private final DatabaseManager dbManager;

    public UserService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void registerUser(String username) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (username, balance) VALUES (?, 0.00)")) {
            statement.setString(1, username);
            statement.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("User already exists.");
            } else {
                System.err.println("Registration failed: " + e.getMessage());
            }
        }
    }

    public void deposit(String username, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET balance = balance + ? WHERE username = ?")) {
            statement.setDouble(1, amount);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Deposit successful.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Deposit failed: " + e.getMessage());
        }
    }

    public void viewBalance(String username) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT balance FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Current balance: " + rs.getDouble("balance"));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve balance: " + e.getMessage());
        }
    }
}
