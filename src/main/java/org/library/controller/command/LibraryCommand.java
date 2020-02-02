package org.library.controller.command;

import org.library.controller.model.services.BookService;

import javax.servlet.http.HttpServletRequest;

public class LibraryCommand implements Command {
    private BookService bookService;

    public LibraryCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("books", bookService.findAllBooksWithReaders());
        System.out.println(bookService.findAllBooksWithReaders().size());
        return "/WEB-INF/admin/library.jsp";
    }
}
