package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AllBooksCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/allbooks.jsp";
    }
}
