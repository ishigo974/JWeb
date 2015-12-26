package com.jweb.servlet;

import com.jweb.beans.News;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;
import com.jweb.forms.UpdateNewForm;
import com.jweb.forms.UpdateUserForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The servlet that handles the admin news view
 * Created by lopes_n on 12/22/15.
 */
public class AdminNewsServlet extends HttpServlet {

    /**
     * Action executed when doing a get request on admin news link
     * <p>
     *     Show the news informations for admins
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
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

    /**
     * Action executed when doing a post request on admin news form
     * <p>
     *     Update the information of the news with the current form
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link UpdateNewForm#UpdateNews(HttpServletRequest)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateNewForm form = new UpdateNewForm();
        form.UpdateNews(request);
        response.sendRedirect("/admin/news");
    }
}

