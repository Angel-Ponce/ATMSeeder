package ATMSeeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Angel Ponce
 */
public class Connecter {

    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=ATM;";
    private static final String USER = "angelATM";
    private static final String PASSWORD = "angel123";
    public PreparedStatement ps;
    public ResultSet rs;
    public Connection con;

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return connection;
    }

}
