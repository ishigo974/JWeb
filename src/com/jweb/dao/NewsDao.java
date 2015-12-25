package com.jweb.dao;

import com.jweb.beans.News;
import com.jweb.beans.User;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/20/15.
 */
public class NewsDao {
    private static final String url = "jdbc:mysql://localhost:3306/jweb_db";
    private static final String user = "jweb";
    private static final String pswd = "jweb";
    Connection bdd = null;

    public NewsDao() throws DBErrors {
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

    public News getNews(int id) throws DBErrors {
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT title, content, id FROM news WHERE id = ?;");
            statement.setInt(1, id);
            result = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        News news;
        try {
            news = new News();
            if (!result.first())
                throw new DBErrors("Can not get the news");
            news.setTitle(result.getString("title"));
            news.setContent(result.getString("content"));
            news.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return news;
    }

    public LinkedList<News> getNews() throws DBErrors{
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT title, content, id FROM news;");
            result = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        News tmp;
        LinkedList<News> news = new LinkedList();
        try {
            while (result.next()) {
                tmp = new News();
                tmp.setTitle(result.getString("title"));
                tmp.setContent(result.getString("content"));
                tmp.setId(result.getInt("id"));
                news.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return news;
    }

    public void setNews(News news) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("INSERT INTO news (title, content) VALUES (?, ?);");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateNews(News simpleNew) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("UPDATE news SET title = ?, content = ? WHERE id = ?;");
            statement.setString(1, simpleNew.getTitle());
            statement.setString(2, simpleNew.getContent());
            statement.setInt(3, simpleNew.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteNews(int id) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("DELETE FROM news WHERE id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}