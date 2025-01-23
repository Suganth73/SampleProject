package User.Info.InvestmentPatform;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/investment_platform";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Aspire@123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
