package org.brs.library.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", servletRequest.getLocale());
        servletRequest.setAttribute("resource", resourceBundle);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
