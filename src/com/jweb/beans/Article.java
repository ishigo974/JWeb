package com.jweb.beans;

/**
 * Created by lopes_n on 12/23/15.
 */
public class Article {
    private String title;
    private String content;
    private String price;
    private String img;
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

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }
}
