package com.jweb.servlet;

import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that handles the news deletion by admin
 * Created by lopes_n on 12/22/15.
 */
public class deleteNewsServlet extends HttpServlet {
    /**
     * Action executed when doing a get request on delete news link
     * <p>
     *     Delete the news selected
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link com.jweb.dao.NewsDao#deleteNews(int)}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsDao bdd;
        if (request.getParameter("id") != null) {
            try {
                bdd = new NewsDao();
                bdd.deleteNews(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
        }
        response.sendRedirect("/admin/news");
    }
}
