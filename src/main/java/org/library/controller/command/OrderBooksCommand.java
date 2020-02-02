package org.library.controller.command;

import org.library.controller.model.entity.Book;
import org.library.controller.model.entity.Order;
import org.library.controller.model.services.BookService;
import org.library.controller.model.services.OrderService;

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


        if (name == null || name.equals("") || date == null || date.equals("")) {
            return "/WEB-INF/user/orderbook.jsp";
        }
        Optional<Book> book;
        Order order;
        if((book=bookService.findBookByName(name)).isPresent() && !book.get().getIsInUse()){
            order = Order.builder()
                    .bookId(book.get().getId())
                    .bookName(name)
                    .dateTo(LocalDate.parse(date))
                    .userName((String) request.getSession().getAttribute("userName"))
                    .usrId((Long) request.getSession().getAttribute("userId"))
                    .build();
            this.orderService.placeOrder(order);
            return "/WEB-INF/user/orderbook.jsp";
        }
        request.setAttribute("message", "Book not found or already taken");
        return "/WEB-INF/user/orderbook.jsp";


    }
}
