package com.jweb.servlet;

import com.jweb.beans.User;
import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;
import com.jweb.forms.LoginForm;
import com.jweb.forms.SignupForm;
import com.jweb.forms.UpdateUserForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The servlet that handles the admin users view
 * Created by menigo_m on 16/12/15.
 */
public class AdminUserServlet extends HttpServlet {
    /**
     * Action executed when doing a get request on admin user link
     * <p>
     *     Show the users informations for admins
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao bdd = null;
        List<User> users = null;
        User user = null;

        if (request.getParameter("id") != null)
        {
            try {
                bdd = new UserDao();
                user = bdd.getUser(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/WEB-INF/updateUser.jsp").forward(request, response);
        }
        else
        {
            try {
                bdd = new UserDao();
                users = bdd.getUsers();
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/WEB-INF/adminUser.jsp").forward(request, response);
        }
    }

    /**
     * Action executed when doing a post request on admin user form
     * <p>
     *     Update the information of the user with the current form
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link UpdateUserForm#UpdateUser(HttpServletRequest)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateUserForm form = new UpdateUserForm();
        form.UpdateUser(request);
        response.sendRedirect("/admin/users");
    }
}
