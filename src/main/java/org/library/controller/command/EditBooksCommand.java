package org.library.controller.command;

import org.library.controller.model.services.BookService;

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
