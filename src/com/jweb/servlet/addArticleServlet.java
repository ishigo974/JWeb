package com.jweb.servlet;

import com.jweb.forms.addArticleForm;
import com.jweb.forms.addNewForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;

/**
 * The servlet that handles the article creation
 * Created by lopes_n on 12/23/15.
 */
public class addArticleServlet extends HttpServlet {

    /**
     * Action executed when doing a get request on admin add article link
     * <p>
     *     Render the add article page
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/addArticle.jsp").forward(request, response);
    }

    /**
     * Action executed when doing a POST request on admin add article form
     * <p>
     *     Add an article in database if the informations are valid
     * </p>
     * @param request HttpServletRequest
     *                The object with the request of the user
     * @param response HttpServletResponse
     *                 The response given by the server to the user
     * {@link addArticleForm#addArticle(HttpServletRequest, String)}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addArticleForm form = new addArticleForm();
        Part part = request.getPart("image");
        String imageFolder = getServletContext().getRealPath("/") + this.getServletConfig().getInitParameter("imageFolder");

        String fileName = getFileName(part);
        if (fileName != null && !fileName.isEmpty()) {
            fileName = fileName.substring( fileName.lastIndexOf( '/' ) + 1 ).substring(fileName.lastIndexOf( '/' ) + 1 );
            Date date = new Date();
            date.getTime();
            fileName = date.getTime() + fileName;
            saveFile(part, fileName, imageFolder);
            form.addArticle(request, fileName);
        }
        else
            form.addArticle(request, "");
        response.sendRedirect("/admin/articles");
    }

    /**
     * Get the name of the image given to the article
     * @param part Part
     *             Part given
     * @return The name of the image
     */
    private static String getFileName(Part part) {
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            if (contentDisposition.trim().startsWith("filename")) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Save the image given
     * @param part Part
     *             The part given
     * @param fileName String
     *                 The name given to the image
     * @param imageFolder String
     *                    The folder where the image is
     * @throws IOException if errors happen
     */
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

