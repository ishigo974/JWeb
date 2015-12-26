package com.jweb.servlet;

import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;
import com.jweb.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that handles the user deletion by admin
 * Created by lopes_n on 12/22/15.
 */
public class deleteUserServlet extends HttpServlet {
    /**
     * Action executed when doing a get request on delete user link
     * <p>
     *     Delete the user selected
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link com.jweb.dao.UserDao#deleteUser(int)}
     */
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
