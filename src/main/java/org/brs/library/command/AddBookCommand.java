package org.brs.library.command;

import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.entity.Book;
import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class AddBookCommand implements Command {

    private BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final String name = request.getParameter("name");
        final String[] authors = request.getParameterValues("author");
        final String attribute = request.getParameter("attribute");

        if (authors == null || ValidationHelper.isNull(name, attribute)) {
            request.setAttribute("books", bookService.findAll());
            return "/WEB-INF/admin/addbook.jsp";
        }

        this.bookService.save(Book.builder()
                .setIsInUse(false)
                .setAuthors(Arrays.asList(authors))
                .setName(name)
                .setAttribute(attribute)
                .build());
        request.setAttribute("books", bookService.findAll());
        return "/WEB-INF/admin/addbook.jsp";
    }
}
