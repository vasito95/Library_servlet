package org.brs.library.helper;

import org.brs.library.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityHelper {

    public static boolean hasAnyPermission(HttpServletRequest request){
        String path = request.getRequestURI();
        HttpSession session = request.getSession();

        return ((path.contains("admin")
                || path.contains("user")) &&
                (session == null ||
                        session.getAttribute("role") == null ||
                        session.getAttribute("role").equals(""))) ||
                        (path.contains("admin") &&
                        session.getAttribute("role").equals(Role.USER));
    }
}
