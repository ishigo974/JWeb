package com.jweb.servlet;

import com.jweb.beans.User;
import com.jweb.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by menigo_m on 16/12/15.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginForm form = new LoginForm();
        User user = null;
        user = form.loginUser(request);
        request.setAttribute("form", form);
        request.setAttribute("user", user);
        HttpSession session = request.getSession();
        if (form.getErrors().isEmpty()) {
            session.setAttribute("userSession", user);
        } else {
            session.setAttribute("userSession", null);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
