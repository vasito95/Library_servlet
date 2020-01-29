package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LibraryCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/library.jsp";
    }
}
