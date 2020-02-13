package org.brs.library.command;

import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class EditBooksCommand implements Command {
    private BookService bookService;

    public EditBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("books", bookService.findAllFreeBooks());
        return "/WEB-INF/admin/editbooks.jsp";
    }
}