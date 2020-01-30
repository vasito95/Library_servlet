package org.library.controller.command;

import org.library.controller.model.services.BookService;

import javax.servlet.http.HttpServletRequest;

public class AllBooksCommand implements Command{
    private BookService bookService;

    public AllBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("books", bookService.findAll());

        return "/WEB-INF/user/allbooks.jsp";
    }
}
