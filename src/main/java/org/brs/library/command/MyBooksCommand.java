package org.brs.library.command;

import org.brs.library.helper.ValidationHelper;
import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class MyBooksCommand implements Command {
    private BookService bookService;

    public MyBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        final String bookId = request.getParameter("bookId");

        if(ValidationHelper.nonNull(bookId)){
            this.bookService.returnBook(Long.parseLong(bookId),(Long) request.getSession().getAttribute("userId"));
        }
        request.setAttribute("books", bookService.findAllByUserId((Long) request.getSession().getAttribute("userId")));
        return "/WEB-INF/user/mybooks.jsp";
    }
}