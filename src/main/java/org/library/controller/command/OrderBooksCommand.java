package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class OrderBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/orderbook.jsp";
    }
}
