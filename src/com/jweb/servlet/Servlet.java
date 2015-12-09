package com.jweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by menigo_m on 08/12/15.
 */
public class Servlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/style/foundation.css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"button\">Ceci est une page générée depuis une servlet.</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
