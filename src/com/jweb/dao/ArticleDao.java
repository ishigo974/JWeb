package com.jweb.dao;

import com.jweb.beans.Article;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/23/15.
 */
public class ArticleDao {
    private static final String url = "jdbc:mysql://localhost:3306/lopes_n";
    private static final String user = "root";
    private static final String pswd = "kevkev";
    Connection bdd = null;

    public ArticleDao() throws DBErrors {
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

    public Article getArticle(int id) throws DBErrors {
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT title, price, content, id FROM articles WHERE id = '" + id + "';" );
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        Article article;
        try {
            article = new Article();
            if (!result.first())
                throw new DBErrors("Can not get the news");
            article.setTitle(result.getString("title"));
            article.setPrice(result.getString("price"));
            article.setContent(result.getString("content"));
            article.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return article;
    }

    public LinkedList<Article> getArticles() throws DBErrors{
        Statement statement;
        ResultSet result;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            result = statement.executeQuery( "SELECT title, price, content, id FROM articles;" );
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        Article tmp;
        LinkedList<Article> articles = new LinkedList();
        try {
            while (result.next()) {
                tmp = new Article();
                tmp.setTitle(result.getString("title"));
                tmp.setPrice(result.getString("price"));
                tmp.setContent(result.getString("content"));
                tmp.setId(result.getInt("id"));
                articles.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return articles;
    }

    public void setArticle(Article article) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("INSERT INTO articles (title, price, content) VALUES ('" + article.getTitle() + "', '" + article.getPrice() + "', '" + article.getContent() + "');");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateArticle(Article article) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("UPDATE articles SET title = '" + article.getTitle() + "', price = '" + article.getPrice() + "', content = '" + article.getContent() +
                    "' WHERE id = " + article.getId() + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteArticle(int id) throws DBErrors {
        Statement statement;
        try {
            statement = bdd.createStatement();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        try {
            statement.executeUpdate("DELETE FROM articles WHERE id = " + id + ";");
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}
