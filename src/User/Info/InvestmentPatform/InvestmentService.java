package User.Info.InvestmentPatform;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvestmentService {
    private final DatabaseManager dbManager;

    public InvestmentService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void invest(String username, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid investment amount.");
            return;
        }

        try (Connection connection = dbManager.getConnection();
             PreparedStatement getUser = connection.prepareStatement(
                     "SELECT balance FROM users WHERE username = ?");
             PreparedStatement updateBalance = connection.prepareStatement(
                     "UPDATE users SET balance = balance - ? WHERE username = ?");
             PreparedStatement addInvestment = connection.prepareStatement(
                     "INSERT INTO investments (username, amount) VALUES (?, ?)")) {

            getUser.setString(1, username);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (amount > balance) {
                    System.out.println("Insufficient balance.");
                    return;
                }
                updateBalance.setDouble(1, amount);
                updateBalance.setString(2, username);
                updateBalance.executeUpdate();

               
                addInvestment.setString(1, username);
                addInvestment.setDouble(2, amount);
                addInvestment.executeUpdate();

                System.out.println("Investment successful.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Investment failed: " + e.getMessage());
        }
    }
}








































//public void viewInvestmentHistory(String username) {
//    try (Connection connection = dbManager.getConnection();
//         PreparedStatement statement = connection.prepareStatement(
//                 "SELECT amount, timestamp FROM investments WHERE username = ?")) {
//        statement.setString(1, username);
//        ResultSet rs = statement.executeQuery();
//        boolean hasHistory = false;
//        while (rs.next()) {
//            hasHistory = true;
//            System.out.printf("Invested: %.2f at %s%n", rs.getDouble("amount"), rs.getString("timestamp"));
//        }
//        if (!hasHistory) {
//            System.out.println("No investments found.");
//        }
//    } catch (SQLException e) {
//        System.err.println("Failed to retrieve investment history: " + e.getMessage());
//    }
//}