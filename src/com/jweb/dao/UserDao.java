package com.jweb.dao;

import com.jweb.beans.User;

import java.sql.*;

/**
 * Created by lopes_n on 12/17/15.
 */
public class UserDao {

    private static final String url = "jdbc:mysql://localhost:3306/lopes_n";
    private static final String user = "root";
    private static final String pswd = "kevkev";
    Connection bdd = null;

    public UserDao() throws DBErrors {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            bdd = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public User getUser(String email, String password) throws DBErrors {
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT id, email, pswd, name FROM users WHERE email = '" + email + "';" );
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        User user;
        try {
            user = new User();
            if (!result.first() || !result.getString("pswd").equals(password))
                throw new DBErrors("Can not log in");
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
        } catch (SQLException e) {
            throw new DBErrors("Can not log in");
        }
        return user;
    }

    public void setUser(User user) throws DBErrors {
        Statement statement = null;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("INSERT INTO users (email, pswd, name) VALUES ('" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getName() + "');");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}