package com.jweb.servlet;

import com.jweb.beans.News;
import com.jweb.beans.User;
import com.jweb.forms.SignupForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by menigo_m on 08/12/15.
 */
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News news = new News();
        news.setTitle("Titre de la news toto");
        news.setContent("Lorem ipsum blabladwkdjwejdwejwej");

        List<News> articles = new ArrayList<>();
        articles.add(news);
        articles.add(news);
        articles.add(news);
        articles.add(news);

        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
