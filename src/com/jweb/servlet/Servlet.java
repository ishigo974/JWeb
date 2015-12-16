package com.jweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by menigo_m on 08/12/15.
 */
public class Servlet extends HttpServlet {
    //    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "Voici un element passé du servlet index à la vue";
        request.setAttribute("example", msg);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
