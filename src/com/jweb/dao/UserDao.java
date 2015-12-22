package com.jweb.dao;

import com.jweb.beans.User;

import java.sql.*;
import java.util.LinkedList;

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
        Statement statement;
        ResultSet result;
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
            user.setPassword(result.getString("pswd"));
            user.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not log in");
        }
        return user;
    }

    public User getUser(int id) throws DBErrors {
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT id, email, pswd, name FROM users WHERE id = '" + String.valueOf(id) + "';" );
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        User user;
        try {
            user = new User();
            if (!result.first())
                throw new DBErrors("Can not log in");
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not log in");
        }
        return user;
    }

    public LinkedList<User> getUsers() throws DBErrors{
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT email, name, newsletter, id FROM users;" );
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        User tmp;
        LinkedList<User> users = new LinkedList();
        try {
            while (result.next()) {
                tmp = new User();
                tmp.setEmail(result.getString("email"));
                tmp.setName(result.getString("name"));
                tmp.setNews(result.getBoolean("newsletter"));
                tmp.setId(result.getInt("id"));
                users.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the users");
        }
        return users;
    }

    public void setUser(User user) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("INSERT INTO users (email, pswd, name, newsletter) VALUES ('" + user.getEmail() + "', '" + user.getPassword() +
                                    "', '" + user.getName() + "', '" + (user.isNews() ? 1 : 0) + "');");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateUser(User user) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("UPDATE users SET email = '" + user.getEmail() + "', pswd = '" + user.getPassword() + "', name = '" + user.getName() +
                                    "', newsletter = '" + (user.isNews() ? 1 : 0) + "' WHERE id = " + user.getId() + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteUser(int id) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}