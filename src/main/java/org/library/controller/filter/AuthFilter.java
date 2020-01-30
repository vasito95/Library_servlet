package org.library.controller.filter;

import org.library.controller.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AUTH");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();
        HttpSession session = req.getSession();
        if (path.contains("admin") &&
                (session == null ||
                        session.getAttribute("role") == null ||
                        session.getAttribute("role").equals("") ||
                        session.getAttribute("role").equals(Role.USER)))
        {
            ((HttpServletResponse) response).sendRedirect("/app/login");
            return;
        }

        if (path.contains("user") &&
                (session == null ||
                session.getAttribute("role") == null ||
                session.getAttribute("role").equals("") ||
                session.getAttribute("role").equals(Role.ADMIN)))
        {
            ((HttpServletResponse) response).sendRedirect("/app/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
