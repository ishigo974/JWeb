package com.jweb.servlet;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getServletContext().getRequestDispatcher("/WEB-INF/addNews.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addNewForm form = new addNewForm();
        form.addNews(request);
        response.sendRedirect("/admin/news");
    }
}
