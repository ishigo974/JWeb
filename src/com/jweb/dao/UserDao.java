package com.jweb.dao;

import com.jweb.beans.User;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/17/15.
 */
public class UserDao {
    private static final String url = "jdbc:mysql://localhost:3306/jweb_db";
    private static final String user = "jweb";
    private static final String pswd = "jweb";
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
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT id, email, pswd, newsletter, admin, name FROM users WHERE email = ?;");
            statement.setString(1, email);
            result = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        User user;
        try {
            user = new User();
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new DBErrors("Can not log in");
            }
            if (password == null)
                throw new DBErrors("Can not log in");
            user.setPassword((new HexBinaryAdapter()).marshal(md5.digest(password.getBytes())));
            if (!result.first() || !result.getString("pswd").equals((new HexBinaryAdapter()).marshal(md5.digest(password.getBytes()))))
                throw new DBErrors("Can not log in");
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("pswd"));
            user.setNews(result.getBoolean("newsletter"));
            user.setAdmin(result.getBoolean("admin"));
            user.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not log in");
        }
        return user;
    }

    public User getUser(int id) throws DBErrors {
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT id, email, pswd, newsletter, admin, name FROM users WHERE id = ?;");
            statement.setInt(1, id);
            result = statement.executeQuery();
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
            user.setNews(result.getBoolean("newsletter"));
            user.setAdmin(result.getBoolean("admin"));
            user.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not log in");
        }
        return user;
    }

    public LinkedList<User> getUsers() throws DBErrors {
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT email, name, newsletter, admin, id FROM users;");
            result = statement.executeQuery();
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
                tmp.setAdmin(result.getBoolean("admin"));
                tmp.setId(result.getInt("id"));
                users.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the users");
        }
        return users;
    }

    public LinkedList<User> getSubscriber() throws DBErrors {
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT email, name, newsletter, id FROM users WHERE newsletter IS TRUE;");
            result = statement.executeQuery();
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
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("INSERT INTO users (email, pswd, name, newsletter, admin) VALUES (?, ?, ?, ?, '0');");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setBoolean(4, user.isNews());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateUser(User user) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("UPDATE users SET email = ?, pswd = ?, name = ?, newsletter = ?, admin = ? WHERE id = ?;");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setBoolean(4, user.isNews());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteUser(int id) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("DELETE FROM users WHERE id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}