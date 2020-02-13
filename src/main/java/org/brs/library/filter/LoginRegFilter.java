package org.brs.library.filter;

import org.brs.library.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginRegFilter", urlPatterns = {"/login", "/registration"})
public class LoginRegFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        if(session != null &&
                session.getAttribute("role") != null &&
                session.getAttribute("role") != "")
        {
            Role role = (Role) session.getAttribute("role");
            if(role.equals(Role.ADMIN)){
                ((HttpServletResponse) response).sendRedirect("/app/admin");
                return;
            }
            if(role.equals(Role.USER)){
                ((HttpServletResponse) response).sendRedirect("/app/user");
                return;
            }
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
