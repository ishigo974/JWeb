package com.jweb.servlet;

import com.jweb.beans.User;
import com.jweb.dao.ArticleDao;
import com.jweb.forms.SignupForm;
import com.jweb.mails.Mailer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by menigo_m on 16/12/15.
 */
public class SignupServlet extends HttpServlet {
    /**
     * Action executed when doing a post request on an signup view
     * <p>
     *     Try to create a user if attributes are good
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link com.jweb.dao.UserDao#setUser(User)}
     * {@link SignupForm#signupUser(HttpServletRequest)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignupForm form = new SignupForm();
        User user = form.signupUser(request);
        if (user != null)
        {
            Mailer.send(user.getEmail(), "Bienvenue !", "Bonjour " + user.getName() +
                    ",\n\nBienvenue sur notre plateforme de news et d'articles JWeb." +
                    "\nVotre inscription a été réussie.\n\n" +
                    "Cordialement,\nMaxime Menigoz et Kevin Lopes");

            request.setAttribute("user", user);
        }
        response.sendRedirect("/");
    }

    /**
     * Action executed when doing a get request on an signup view
     * <p>
     *     Show the signup form
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }
}
