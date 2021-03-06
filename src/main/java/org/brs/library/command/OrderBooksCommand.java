package org.brs.library.command;

import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;
import org.brs.library.service.BookService;
import org.brs.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class OrderBooksCommand implements Command {

    public static final int DAYS_TO_ADD = 30;
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
        request.setAttribute("minDate", LocalDate.now());
        request.setAttribute("maxDate", LocalDate.now().plusDays(DAYS_TO_ADD));
        if (ValidationHelper.isNull(name, date)) {
            return "/WEB-INF/user/orderbook.jsp";
        }
        LocalDate dateParsed;
        if (date.equals("") || (dateParsed = LocalDate.parse(date)).isBefore(LocalDate.now()) || dateParsed.isAfter(LocalDate.now().plusDays(30))) {
            request.setAttribute("message", "Date is not correct");
            return "/WEB-INF/user/orderbook.jsp";
        }

        Optional<Book> book;
        if ((book = bookService.findBookByName(name)).isPresent() && !book.get().getIsInUse()) {
            this.orderService.placeOrder(Order.builder()
                    .setBookId(book.get().getId())
                    .setBookName(name)
                    .setDateTo(dateParsed)
                    .setUserName((String) request.getSession().getAttribute("userName"))
                    .setUserId((Long) request.getSession().getAttribute("userId"))
                    .build());
            return "/WEB-INF/user/orderbook.jsp";
        }
        request.setAttribute("message", "Book not found or already taken");
        return "/WEB-INF/user/orderbook.jsp";
    }
}
