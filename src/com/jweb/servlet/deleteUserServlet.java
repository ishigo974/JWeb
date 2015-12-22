package com.jweb.servlet;

import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lopes_n on 12/22/15.
 */
public class deleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao bdd;
        if (request.getParameter("id") != null) {
            try {
                bdd = new UserDao();
                bdd.deleteUser(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
        }
        response.sendRedirect("/admin/users");
    }
}
