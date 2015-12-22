package com.jweb.dao;

import com.jweb.beans.News;
import com.jweb.beans.User;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/20/15.
 */
public class NewsDao {
    private static final String url = "jdbc:mysql://localhost:3306/lopes_n";
    private static final String user = "root";
    private static final String pswd = "kevkev";
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
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT title, content, id FROM news WHERE id = '" + id + "';" );
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
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT title, content, id FROM news;" );
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
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("INSERT INTO news (title, content) VALUES ('" + news.getTitle() + "', '" + news.getContent() + "');");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateNews(News simpleNew) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("UPDATE news SET title = '" + simpleNew.getTitle() + "', content = '" + simpleNew.getContent() +
                                    "' WHERE id = " + simpleNew.getId() + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteNews(int id) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("DELETE FROM news WHERE id = " + id + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}