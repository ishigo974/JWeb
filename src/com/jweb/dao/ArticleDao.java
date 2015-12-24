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
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT title, price, content, img, id FROM articles WHERE id = ?;");
            statement.setInt(1, id);
            result = statement.executeQuery();
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
            article.setImg(result.getString("img"));
            article.setId(result.getInt("id"));
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return article;
    }

    public LinkedList<Article> getArticles() throws DBErrors{
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT title, price, content, img, id FROM articles;");
            result = statement.executeQuery();
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
                tmp.setImg(result.getString("img"));
                tmp.setId(result.getInt("id"));
                articles.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return articles;
    }

    public void setArticle(Article article) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("INSERT INTO articles (title, price, content, img) VALUES (?, ?, ?, ?);");
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getPrice());
            statement.setString(3, article.getContent());
            statement.setString(4, article.getImg());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void updateArticle(Article article) throws DBErrors {
        PreparedStatement statement;
        try {
            if (article.getImg().isEmpty()) {
                statement = bdd.prepareStatement("UPDATE articles SET title = ?, price = ?, content = ? WHERE id = ?;");
                statement.setString(1, article.getTitle());
                statement.setString(2, article.getPrice());
                statement.setString(3, article.getContent());
                statement.setInt(4, article.getId());
            } else
            {
                statement = bdd.prepareStatement("UPDATE articles SET title = ?, price = ?, content = ?, img = ? WHERE id = ?;");
                statement.setString(1, article.getTitle());
                statement.setString(2, article.getPrice());
                statement.setString(3, article.getContent());
                statement.setString(4, article.getImg());
                statement.setInt(5, article.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public void deleteArticle(int id) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("DELETE FROM articles WHERE id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }
}
