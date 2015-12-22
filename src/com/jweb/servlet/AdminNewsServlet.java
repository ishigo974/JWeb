package com.jweb.servlet;

import com.jweb.beans.News;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;
import com.jweb.forms.UpdateNewForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lopes_n on 12/22/15.
 */
public class AdminNewsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NewsDao bdd = null;
        List<News> news = null;
        News simpleNew = null;

        if (request.getParameter("id") != null)
        {
            try {
                bdd = new NewsDao();
                simpleNew = bdd.getNews(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("simpleNew", simpleNew);
            request.getServletContext().getRequestDispatcher("/WEB-INF/updateNews.jsp").forward(request, response);
        }
        else
        {
            try {
                bdd = new NewsDao();
                news = bdd.getNews();
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("news", news);
            request.getServletContext().getRequestDispatcher("/WEB-INF/adminNews.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateNewForm form = new UpdateNewForm();
        form.UpdateNews(request);
        response.sendRedirect("/admin/news");
    }
}

