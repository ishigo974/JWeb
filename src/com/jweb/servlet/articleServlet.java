package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lopes_n on 12/23/15.
 */
public class articleServlet extends HttpServlet {

    /**
     * Action executed when doing a get request on articles link
     * <p>
     *     Show all the articles
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link ArticleDao#getArticles()}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleDao bdd;
        List<Article> articles = null;

            try {
                bdd = new ArticleDao();
                articles = bdd.getArticles();
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("articles", articles);
            request.getServletContext().getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
