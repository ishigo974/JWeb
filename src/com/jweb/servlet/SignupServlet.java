package com.jweb.servlet;

import com.jweb.beans.User;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }
}
