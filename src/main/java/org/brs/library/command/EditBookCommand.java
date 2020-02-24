package org.brs.library.command;

import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.entity.Book;
import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EditBookCommand implements Command {
    private BookService bookService;

    public EditBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String[] authors = request.getParameterValues("author");
        final String attribute = request.getParameter("attribute");
        final Long bookId = Long.parseLong(request.getParameter("bookId"));
        if (ValidationHelper.isNull(bookId)) {
            return "redirect:/admin/edit-books";
        }
        if (request.getRequestURI().endsWith("accept")) {
            List<String> authorsLit = Arrays.stream(authors).filter(s -> !s.equals("")).collect(Collectors.toList());
            bookService.update(Book.builder()
                    .setId(bookId)
                    .setName(name)
                    .setAttribute(attribute)
                    .setAuthors(authorsLit)
                    .build());
            return "redirect:/admin/edit-books";
        }
        if (request.getRequestURI().endsWith("delete")) {
            bookService.delete(bookId);
            return "redirect:/admin/edit-books";
        }
        Optional<Book> optionalBook = bookService.findById(bookId);
        optionalBook.ifPresent(book -> request.setAttribute("book", optionalBook.get()));
        return "/WEB-INF/admin/editbook.jsp";
    }
}
