package org.library.controller.command;

import org.library.controller.model.entity.Role;
import org.library.controller.model.entity.User;
import org.library.controller.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String pass = request.getParameter("password");


        if (email == null || email.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }
        Optional<User> userOptional = userService.findUserByEmailAndPassword(email.trim(), pass);
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
        Set<Role> userRole;
        User u;
        if (userOptional.isPresent()) {
            u = userOptional.get();
        } else {
            return "/login.jsp";
        }
        if (loggedUsers != null) {
            if (loggedUsers.contains(u.getEmail())) {
                return "/error";
            }
        } else {
            loggedUsers = new HashSet<String>();
        }

        loggedUsers.add(u.getEmail());
        session.getServletContext().setAttribute("loggedUsers", loggedUsers);
        session.setAttribute("userEmail", u.getEmail());
        userRole = u.getRoles();

        if (userRole.contains(Role.ADMIN)) {
            session.setAttribute("role", Role.ADMIN);
            return "redirect:/admin";
        }
        if (userRole.contains(Role.USER)) {
            session.setAttribute("role", Role.USER);
            return "redirect:/user";
        }
        return "/index.jsp";
    }
}
