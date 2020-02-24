package org.brs.library.command;

import org.brs.library.exception.OperationFailedException;
import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.entity.Role;
import org.brs.library.model.entity.User;
import org.brs.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter("username");
        final String phone = request.getParameter("phoneNumber");
        final String email = request.getParameter("email");
        final String pass = request.getParameter("password");

        if (ValidationHelper.isNull(name, phone, email, pass)) {
            return "/registration.jsp";
        }
        if (ValidationHelper.validateRegistrationForm(name, phone, email, pass)) {
            request.setAttribute("error", "error.user.exist");
            return "/registration.jsp";
        }
        User user = User.builder()
                .setUsername(name)
                .setEmail(email)
                .setPassword(pass)
                .setIsActive(true)
                .setRole(Role.USER)
                .setPhoneNumber(phone)
                .build();
        try {
            userService.createUser(user);
        } catch (OperationFailedException ex) {
            request.setAttribute("error", "error.user.exist");
            return "/registration.jsp";
        }
        return "redirect:/login";
    }
}
