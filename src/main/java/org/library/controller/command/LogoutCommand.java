package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("userEmail");
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(userEmail);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        request.getSession().removeAttribute("userEmail");
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("role");
        request.getSession().removeAttribute("userId");
        return "redirect:/login";
    }
}
