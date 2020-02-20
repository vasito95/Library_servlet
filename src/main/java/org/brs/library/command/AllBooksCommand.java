package org.brs.library.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brs.library.helper.ValidationHelper;
import org.brs.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class AllBooksCommand implements Command {
    private static Logger LOG = LogManager.getLogger(AllBooksCommand.class);
    private BookService bookService;

    public AllBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final String search = request.getParameter("search");
        final String field = request.getParameter("field");

        if (ValidationHelper.isNull(search, field) || search.equals("")) {
            request.setAttribute("books", bookService.findAll());
            return "/WEB-INF/user/allbooks.jsp";
        }
        switch (field) {
            case "name":
                request.setAttribute("books", bookService.findAllByNameLike("%" + search + "%"));
                break;
            case "author":
                request.setAttribute("books", bookService.findAllByAuthorName("%" + search + "%"));
                break;
            case "attribute":
                request.setAttribute("books", bookService.findAllByAttributeLike("%" + search + "%"));
                break;
            default:
                request.setAttribute("books", bookService.findAll());
                break;
        }
        return "/WEB-INF/user/allbooks.jsp";
    }
}
