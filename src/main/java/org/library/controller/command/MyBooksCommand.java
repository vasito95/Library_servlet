package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class MyBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/mybooks.jsp";
    }
}
