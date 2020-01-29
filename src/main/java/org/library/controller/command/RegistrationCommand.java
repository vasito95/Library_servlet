package org.library.controller.command;

import org.library.controller.model.entity.Role;
import org.library.controller.model.entity.User;
import org.library.controller.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;


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

        if( name == null || name.equals("") ||
                pass == null || pass.equals("") ||
                phone == null || phone.equals("") ||
                email == null || email.equals("")
        ){
            return "/registration.jsp";
        }
            User user = User.builder()
                    .username(name)
                    .email(email)
                    .password(pass)
                    .isActive(true)
                    .roles(Collections.singleton(Role.ADMIN))
                    .phoneNumber(phone)
                    .build();
            userService.createUser(user);
        return "redirect:/login";
    }
}
