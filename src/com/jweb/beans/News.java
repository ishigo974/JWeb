package com.jweb.beans;

/**
 * The news object of the database
 * Created by menigo_m on 15/12/15.
 */
public class News {
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
