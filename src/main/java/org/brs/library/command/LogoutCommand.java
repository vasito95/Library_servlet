package org.brs.library.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().removeAttribute("userEmail");
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("role");
        request.getSession().removeAttribute("userId");
        return "redirect:/login";
    }
}
