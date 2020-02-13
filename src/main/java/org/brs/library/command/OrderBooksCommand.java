package org.brs.library.command;

import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;
import org.brs.library.service.BookService;
import org.brs.library.service.OrderService;
import org.brs.library.utility.ValidationHelper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class OrderBooksCommand implements Command {

    private OrderService orderService;
    private BookService bookService;

    public OrderBooksCommand(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final String name = request.getParameter("name");
        final String date = request.getParameter("dateTo");

        if (ValidationHelper.isStringsNullOrEmpty(name, date)) {
            return "/WEB-INF/user/orderbook.jsp";
        }

        Optional<Book> book;
        if ((book = bookService.findBookByName(name)).isPresent() && !book.get().getIsInUse()) {
            this.orderService.placeOrder(Order.builder()
                    .setBookId(book.get().getId())
                    .setBookName(name)
                    .setDateTo(LocalDate.parse(date))
                    .setUserName((String) request.getSession().getAttribute("userName"))
                    .setUserId((Long) request.getSession().getAttribute("userId"))
                    .build());
            return "/WEB-INF/user/orderbook.jsp";
        }
        request.setAttribute("message", "Book not found or already taken");
        return "/WEB-INF/user/orderbook.jsp";
    }
}
