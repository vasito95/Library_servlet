package org.brs.library.command;

import org.brs.library.model.entity.User;
import org.brs.library.service.UserService;
import org.brs.library.utility.ValidationHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        if (ValidationHelper.isNull(email, password) || email.equals("") || password.equals("")) {
            return "/login.jsp";
        }

        Optional<User> userOptional = userService.findUserByEmailAndPassword(email.trim(), password);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return "/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userName", user.getUsername());
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRoles().iterator().next());
        return "redirect:/user";
    }
}
