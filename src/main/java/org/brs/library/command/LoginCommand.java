package org.brs.library.command;

import org.brs.library.exception.UserNotFoundException;
import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.entity.User;
import org.brs.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        if (ValidationHelper.isStringNullOrEmpty(email, password)) {
            return "/login.jsp";
        }
        if (!ValidationHelper.validateLoginForm(email, password)) {
            request.setAttribute("error", "Form not valid");
            return "/login.jsp";
        }
        User user;
        try {
            user = userService.findUserByEmailAndPassword(email.trim(), password);
        } catch (UserNotFoundException e) {
            request.setAttribute("error", "User not found");
            return "/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userName", user.getUsername());
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        return "redirect:/user";
    }
}
