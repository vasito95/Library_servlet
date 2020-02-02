package org.library.controller.command;

import org.library.controller.model.entity.Book;
import org.library.controller.model.services.BookService;

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

        if(authors == null ||
                attribute == null ||
                attribute.equals("") ||
                name == null ||
                name.equals("")
        ){

            return "/WEB-INF/admin/addbook.jsp";
        }

        Book book = Book.builder()
                .isInUse(false)
                .authors(Arrays.asList(authors))
                .name(name)
                .attribute(attribute)
                .build();
        this.bookService.save(book);


        return "/WEB-INF/admin/addbook.jsp";
    }
}
