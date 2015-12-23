package com.jweb.beans;

/**
 * Created by lopes_n on 12/23/15.
 */
public class Article {
    private String title;
    private String content;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
