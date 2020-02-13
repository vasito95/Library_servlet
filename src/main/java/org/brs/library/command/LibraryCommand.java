package org.brs.library.command;

import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class LibraryCommand implements Command {
    private BookService bookService;

    public LibraryCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("books", bookService.findAllBooksWithReaders());
        return "/WEB-INF/admin/library.jsp";
    }
}
