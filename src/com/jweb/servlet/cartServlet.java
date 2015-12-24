package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/24/15.
 */
public class cartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article;
        ArticleDao bdd;
        HttpSession session = request.getSession();
        if (session.getAttribute("userSession") == null)
            response.sendRedirect("/");
        else {
            if (request.getParameter("id") != null) {
                try {
                    bdd = new ArticleDao();
                    article = bdd.getArticle(Integer.parseInt(request.getParameter("id")));
                    ((LinkedList<Article>) session.getAttribute("userCart")).add(article);
                } catch (DBErrors dbErrors) {
                }
                response.sendRedirect("/article/view?id=" + request.getParameter("id"));
            }
            else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
            }
        }
    }
}
