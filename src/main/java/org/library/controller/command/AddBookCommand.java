package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/addbook.jsp";
    }
}
