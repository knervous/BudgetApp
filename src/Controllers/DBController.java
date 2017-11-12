/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Budget;
import Models.UserAccount;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class DBController {

    //SQL Connection
    private String connectionString = "";
    private Connection connection;
    private String driverString = "";
    private String table;

    public String createAccount(String username, String password, String firstname, String lastname, String email, String creationDate, String gender, String picUrl) {

        String state = "";

        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionString);
            Statement stmt = connection.createStatement();

            String check = "SELECT * FROM " + table + " WHERE username='" + username + "'";
            ResultSet checker = stmt.executeQuery(check);
            if (checker.next()) {
                state = "Duplicate";
            } else {
                //String SQL = "INSERT INTO dbo.Users (username, password, creationdate) ";
                String SQL = "INSERT INTO " + table + " (username,password,firstname,lastname,email,gender,creationdate,budget,picurl,purchases) "
                        + "VALUES ('" + username + "' ,'" + password + "' ,'" + firstname + "' ,'" + lastname + "' ,'" + email + "' ,'"
                        + gender + "' ,'" + creationDate + "' ,'[]','" + picUrl + "' , '[]')";

                stmt.execute(SQL);

                state = "Success";
            }
        } catch (Exception e) {
            state = "Failed";
        } finally {
            close(connection);
        }

        return state;
    }

    public String tryLogin(String username, String password, UserAccount account) {

        if (!(username.length() > 0)) {
            return "UFail";
        }
        if (!(password.length() > 0)) {
            return "PFail";
        }
        String databaseUsername = "";
        String databasePassword = "";
        String budget = "";
        String state = "";
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionString);
            Statement stmt = connection.createStatement();
            String SQL = "SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'";

            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                databaseUsername = rs.getString("username");
                databasePassword = rs.getString("password");
                budget = rs.getString("budget");
                ArrayList<Budget> budgets = new JSONDeserializer<ArrayList<Budget>>().deserialize(budget);
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String creationDate = rs.getString("creationdate");
                String picUrl = rs.getString("picurl");

                account.initFromDB(databaseUsername, databasePassword, firstname, lastname, email, gender, picUrl, creationDate, budgets);
            }

            if (databaseUsername.equals(username) && databasePassword.equals(password)) {
                return "Success";
            } else {
                return "Failed";
            }

        } catch (Exception e) {
            return "NoConnection";
        } finally {
            close(connection);
        }

    }

    public void updateAccount(UserAccount account) {
        String budgets = "";
        try {
            Class.forName("flexjson.JSONSerializer");
            JSONSerializer j = new JSONSerializer();

            budgets = j.include("purchases").serialize(account.getBudgets());
            System.out.println("SERIALISED: " + budgets);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionString);
            Statement stmt = connection.createStatement();

            String SQL = "UPDATE " + table + " "
                    + "SET budget='" + budgets + "' "
                    + "WHERE username='" + account.getUsername() + "' AND password='" + account.getPassword() + "'";

            stmt.execute(SQL);

        } catch (Exception e) {
            System.out.println("Failed updating");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private void close(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {
            }
        }
    }

    public void localLogin() {

        try {

            Class.forName("org.sqlite.JDBC");
            Connection conns = DriverManager.getConnection("jdbc:sqlite:budgetdb.db");
            Statement stmt = conns.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS Users\n"
                    + "(\n"
                    + "USER_ID int IDENTITY(1,1) PRIMARY KEY,\n"
                    + "username varchar(255) NOT NULL,\n"
                    + "password varchar(255) NOT NULL,\n"
                    + "firstname varchar(255),\n"
                    + "lastname varchar(255),\n"
                    + "email varchar(255),\n"
                    + "gender varchar(10),\n"
                    + "creationdate datetime,\n"
                    + "budget varchar(3000),\n"
                    + "purchases varchar(3000),\n"
                    + "picurl varchar(128)\n"
                    + ")";
            stmt.execute(SQL);
            close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void localOrRemoteLogin(boolean isLocal) throws SQLException, ClassNotFoundException {

        connectionString = isLocal ? "jdbc:sqlite:budgetdb.db" : "jdbc:sqlserver://financebudgetapp.database.windows.net:1433;"
                + "database=budgetdb;"
                + "user=paul@financebudgetapp;"
                + "password=Password!;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";

        driverString = isLocal ? "org.sqlite.JDBC" : "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        table = isLocal ? "Users" : "dbo.Users";
        if (isLocal) {
            localLogin();
        }
    }

}
