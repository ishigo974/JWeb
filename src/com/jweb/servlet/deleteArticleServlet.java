package com.jweb.servlet;

import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lopes_n on 12/23/15.
 */
public class deleteArticleServlet extends HttpServlet {
    /**
     * Action executed when doing a get request on delete article link
     * <p>
     *     Delete the article selected
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link com.jweb.dao.ArticleDao#deleteArticle(int)}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao bdd;
        if (request.getParameter("id") != null) {
            try {
                bdd = new ArticleDao();
                bdd.deleteArticle(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
        }
        response.sendRedirect("/admin/articles");
    }
}
