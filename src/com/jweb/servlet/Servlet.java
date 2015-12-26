package com.jweb.servlet;

import com.jweb.beans.News;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The servlet that handles the home page
 * Created by menigo_m on 08/12/15.
 */
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Action executed when doing a get request on an index view
     * <p>
     * Show the last news
     * </p>
     *
     * @param request  HttpServletRequest
     *                 The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsDao bdd;
        List<News> articles = null;
        try {
            bdd = new NewsDao();
            articles = bdd.getNews();
        } catch (DBErrors dbErrors) {

        }

        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
