package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.beans.User;
import com.jweb.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The servlet that handles the login page
 * Created by menigo_m on 16/12/15.
 */
public class LoginServlet extends HttpServlet {
    /**
     * Action executed when doing a post request on login form
     * <p>
     *     Log in the user with the form's informations
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginForm form = new LoginForm();
        LinkedList <Article> cart = new LinkedList<Article>();
        User user = null;
        user = form.loginUser(request);
        request.setAttribute("form", form);
        request.setAttribute("user", user);
        HttpSession session = request.getSession();
        if (form.getErrors().isEmpty()) {
            session.setAttribute("userSession", user);
            session.setAttribute("userCart", cart);
        } else {
            session.setAttribute("userSession", null);
            session.setAttribute("userCart", null);
        }
        response.sendRedirect("/");
    }

    /**
     * Action executed when doing a get request on an login view
     * <p>
     *     Show the login form
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link com.jweb.dao.UserDao#getUser(int)}
     * {@link LoginForm#loginUser(HttpServletRequest)}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
