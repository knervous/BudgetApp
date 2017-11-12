package financebudgetapp.Database;

import java.sql.*;

public class SQLDBConnect {


    public SQLDBConnect() {

    }

   
    public Connection connect() {
        
        String connectionString
                = "jdbc:sqlserver://financebudgetapp.database.windows.net:1433;"
                + "database=budgetdb;"
                + "user=paul@financebudgetapp;"
                + "password=Password!;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";

        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Database connected");

        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return connection;
    }

    private void close(Connection c) {
        if (c != null) {
            try {
                c.close();
                System.out.println("Database closed");
            } catch (Exception e) {
            }
        }
    }

}
