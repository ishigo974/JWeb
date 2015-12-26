package com.jweb.dao;

import com.jweb.beans.News;
import com.jweb.beans.User;

import java.sql.*;
import java.util.LinkedList;

/**
 * The object required to access the News object
 * Created by lopes_n on 12/20/15.
 */
public class NewsDao {
    private static final String url = "jdbc:mysql://localhost:3306/lopes_n";
    private static final String user = "root";
    private static final String pswd = "kevkev";
    Connection bdd = null;

    /**
     * Create the connection with the database
     * @throws DBErrors if the credentials are invalid
     */
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

    /**
     * Get a news by id
     * @param id int
     *           The id of the news in database
     * @return The news with this id
     * @throws DBErrors if select is invalid
     */
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

    /**
     * Get all the news
     * @return LinkedList
     *         The list of the news in database
     * @throws DBErrors if select is invalid
     */
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

    /**
     * Create a news entry in database
     * @param news News
     *             The news object
     * @throws DBErrors if insert is invalid
     */
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

    /**
     * Update a news in database
     * @param simpleNew News
     *                  The new news object
     * @throws DBErrors if update is invalid
     */
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

    /**
     * Delete a news with it's id
     * @param id int
     *           The id of the news to delete
     * @throws DBErrors if delete is invalid
     */
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