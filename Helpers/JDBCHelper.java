package Helpers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHelper {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static Connection getConnection() {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/soccer?useSSL=false";
            String user = "root";
            String password = "root";

            if (con == null) {
                con = DriverManager.getConnection(url, user, password);
            }
            return con;

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("JDBCHelper.getConnection");
        }
        return null;
    }

    public static void executeUpdate(String query) {
        try {
            con = JDBCHelper.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCHelper.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
