package org.brs.library.filter;

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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();
        HttpSession session = req.getSession();
        if ((path.contains("admin") || path.contains("user")) &&
                (session == null ||
                        session.getAttribute("role") == null ||
                        session.getAttribute("role").equals("")))
        {
            res.sendRedirect("/app/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
