package com.jweb.filters;

import com.jweb.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lopes_n on 12/23/15.
 */

public class adminFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {}

    /**
     * Filter the access to specific views
     * <p>
     *     It allows admins to reach the admin page
     * </p>
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        if (session.getAttribute("userSession") == null || !((User) session.getAttribute("userSession")).isAdmin()) {
            response.sendRedirect("/");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}