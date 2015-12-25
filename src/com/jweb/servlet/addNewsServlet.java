package com.jweb.servlet;

import com.jweb.beans.News;
import com.jweb.beans.User;
import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;
import com.jweb.forms.addNewForm;
import com.jweb.mails.Mailer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by lopes_n on 12/22/15.
 */
public class addNewsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getServletContext().getRequestDispatcher("/WEB-INF/addNews.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addNewForm form = new addNewForm();
        News news;
        news = form.addNews(request);
        if (news != null) {
            UserDao db;
            LinkedList<User> users = null;
            try {
                db = new UserDao();
                users = db.getSubscriber();
            } catch (DBErrors dbErrors) {
                dbErrors.printStackTrace();
            }
            for (int i = 0; i < users.size(); i++) {
                Mailer.send(users.get(i).getEmail(), "Du nouveau sur JWeb !", "Bonjour " + users.get(i).getName() +
                        ",\n\nIl y a du nouveau sur Jweb !" +
                        "\nUne nouvelle news viens d'etre publiée:\n\n" +
                        news.getTitle() + "\n\n" + news.getContent() + "\n\n\n" +
                        "Cordialement,\nMaxime Menigoz et Kevin Lopes");
	        }
        }
        response.sendRedirect("/admin/news");
    }
}
