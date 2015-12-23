package com.jweb.beans;

/**
 * Created by menigo_m on 16/12/15.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private int id;
    private boolean news;
    private boolean admin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public boolean isNews() { return news; }

    public void setNews(boolean news) { this.news = news; }

    public boolean isAdmin() { return admin; }

    public void setAdmin(boolean admin) { this.admin = admin; }
}
