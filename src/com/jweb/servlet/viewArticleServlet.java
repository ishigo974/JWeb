package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.beans.Comment;
import com.jweb.beans.User;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.CommentDao;
import com.jweb.dao.DBErrors;
import com.jweb.forms.addCommentForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lopes_n on 12/24/15.
 */
public class viewArticleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Article article = null;
        LinkedList<Comment> comments = null;

        if (request.getParameter("id") != null)
        {
            try {
                ArticleDao bdd;
                bdd = new ArticleDao();
                article = bdd.getArticle(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            try {
                CommentDao bdd;
                bdd = new CommentDao();
                comments = bdd.getComments(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("article", article);
            request.setAttribute("comments", comments);
            request.getServletContext().getRequestDispatcher("/WEB-INF/viewArticle.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userSession") == null)
            response.sendRedirect("/");
        else {
            addCommentForm form = new addCommentForm();
            Comment comment = form.addComment(request, ((User) session.getAttribute("userSession")).getName());
            response.sendRedirect("/article/view?id=" + comment.getArticle());
        }
    }
}
