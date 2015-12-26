package com.jweb.beans;

/**
 * The comment object of the database
 * Created by lopes_n on 12/24/15.
 */
public class Comment {
    private String name;
    private String content;
    private int article;
    private int id;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getArticle() { return article; }

    public void setArticle(int article) { this.article = article; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
