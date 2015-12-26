package com.jweb.servlet;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;
import com.jweb.forms.UpdateArticleForm;
import com.jweb.forms.UpdateUserForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * The servlet that handles the admin articles view
 * Created by lopes_n on 12/23/15.
 */
public class AdminArticleServlet extends HttpServlet {

    /**
     * Action executed when doing a get request on admin articles link
     * <p>
     *     Show the informations of the articles
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link UpdateUserForm#UpdateUser(HttpServletRequest)}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArticleDao bdd = null;
        List<Article> articles = null;
        Article article = null;

        if (request.getParameter("id") != null)
        {
            try {
                bdd = new ArticleDao();
                article = bdd.getArticle(Integer.parseInt(request.getParameter("id")));
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("article", article);
            request.getServletContext().getRequestDispatcher("/WEB-INF/updateArticle.jsp").forward(request, response);
        }
        else
        {
            try {
                bdd = new ArticleDao();
                articles = bdd.getArticles();
            } catch (DBErrors dbErrors) {
            }
            request.setAttribute("articles", articles);
            request.getServletContext().getRequestDispatcher("/WEB-INF/adminArticle.jsp").forward(request, response);
        }
    }

    /**
     * Action executed when doing a post request on admin articles form
     * <p>
     *     Update the information of the article with the current form
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link UpdateArticleForm#UpdateArticle(HttpServletRequest, String)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateArticleForm form = new UpdateArticleForm();
        Part part = request.getPart("image");
        String imageFolder = getServletContext().getRealPath("/") + this.getServletConfig().getInitParameter("imageFolder");

        String fileName = getFileName(part);
        if (fileName != null && !fileName.isEmpty()) {
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('/') + 1);
            Date date = new Date();
            date.getTime();
            fileName = date.getTime() + fileName;
            saveFile(part, fileName, imageFolder);
            form.UpdateArticle(request, fileName);
        }
        else
            form.UpdateArticle(request, "");
        response.sendRedirect("/admin/articles");
    }

    private static String getFileName(Part part) {
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            if (contentDisposition.trim().startsWith("filename")) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void saveFile(Part part, String fileName, String imageFolder) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;

        try {
            entree = new BufferedInputStream(part.getInputStream(), 10240);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(imageFolder + fileName)), 10240);
            byte[] tampon = new byte[10240];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {}
            try {
                entree.close();
            } catch ( IOException ignore ) {}
        }
    }
}
