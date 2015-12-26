package com.jweb.servlet;

import com.jweb.forms.UpdateUserForm;
import com.jweb.forms.addNewForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lopes_n on 12/22/15.
 */
public class addNewsServlet extends HttpServlet {

    /**
     * Action executed when doing a get request on admin add news link
     * <p>
     *     Render the add news page
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getServletContext().getRequestDispatcher("/WEB-INF/addNews.jsp").forward(request, response);
        }

    /**
     * Action executed when doing a POST request on admin add news form
     * <p>
     *     Add a news in database if the informations are valid
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link addNewForm#addNews(HttpServletRequest)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addNewForm form = new addNewForm();
        form.addNews(request);
        response.sendRedirect("/admin/news");
    }
}
