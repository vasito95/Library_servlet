package org.brs.library.filter;

import org.brs.library.helper.SecurityHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        if (SecurityHelper.hasAnyPermission(req)){
            res.sendRedirect("/app/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
