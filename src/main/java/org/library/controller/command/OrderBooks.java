package org.library.controller.command;

import javax.servlet.http.HttpServletRequest;

public class OrderBooks implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/orderbook.jsp";
    }
}
