package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;
import com.jweb.forms.UpdateArticleFrom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lopes_n on 12/23/15.
 */
public class AdminArticleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleDao bdd = null;
        List<Article> articles = null;
        Article article = null;

        if (request.getParameter("id") != null)
        {
            try {
                bdd = new ArticleDao();
                article = bdd.getArticle(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("article", article);
            request.getServletContext().getRequestDispatcher("/WEB-INF/updateArticle.jsp").forward(request, response);
        }
        else
        {
            try {
                bdd = new ArticleDao();
                articles = bdd.getArticles();
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("articles", articles);
            request.getServletContext().getRequestDispatcher("/WEB-INF/adminArticle.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateArticleFrom form = new UpdateArticleFrom();
        form.UpdateArticle(request);
        response.sendRedirect("/admin/articles");
    }
}
