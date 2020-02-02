package org.library.controller.command;

import org.library.controller.model.services.BookService;

import javax.servlet.http.HttpServletRequest;

public class MyBooksCommand implements Command {
    private BookService bookService;

    public MyBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("books", bookService.findAllByUserId((Long) request.getSession().getAttribute("userId")));
        return "/WEB-INF/user/mybooks.jsp";
    }
}
