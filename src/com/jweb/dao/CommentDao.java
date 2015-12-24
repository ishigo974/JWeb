package com.jweb.dao;

import com.jweb.beans.Comment;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/24/15.
 */
public class CommentDao {

    private static final String url = "jdbc:mysql://localhost:3306/lopes_n";
    private static final String user = "root";
    private static final String pswd = "kevkev";
    Connection bdd = null;

    public CommentDao() throws DBErrors {
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
    public void setComment(Comment comment) throws DBErrors {
        PreparedStatement statement;
        try {
            statement = bdd.prepareStatement("INSERT INTO comments (name, content, article) VALUES (?, ?, ?);");
            statement.setString(1, comment.getName());
            statement.setString(2, comment.getContent());
            statement.setInt(3, comment.getArticle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
    }

    public LinkedList<Comment> getComments(int article) throws DBErrors{
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = bdd.prepareStatement("SELECT name, content, article, id FROM comments WHERE article = ?;");
            statement.setInt(1, article);
            result = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBErrors(e.getMessage());
        }
        Comment tmp;
        LinkedList<Comment> comments = new LinkedList();
        try {
            while (result.next()) {
                tmp = new Comment();
                tmp.setName(result.getString("name"));
                tmp.setContent(result.getString("content"));
                tmp.setArticle(result.getInt("article"));
                tmp.setId(result.getInt("id"));
                comments.add(tmp);
            }
        } catch (SQLException e) {
            throw new DBErrors("Can not get the news");
        }
        return comments;
    }
}
