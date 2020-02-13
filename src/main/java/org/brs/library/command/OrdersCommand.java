package org.brs.library.command;

import org.brs.library.service.BookService;
import org.brs.library.service.OrderService;
import org.brs.library.utility.ValidationHelper;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command {
    private OrderService orderService;
    private BookService bookService;

    public OrdersCommand(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String id = request.getParameter("orderId");

        if (ValidationHelper.isStringsNullOrEmpty(id)) {
            request.setAttribute("orders", orderService.findAll());
            return "/WEB-INF/admin/orders.jsp";
        }
        Long orderId = Long.parseLong(id);

        processOrder(request, orderId);
        return "redirect:/admin/orders";

    }

    private void processOrder(HttpServletRequest request, Long orderId) {
        if (request.getRequestURI().endsWith("accept")) {
            orderService.findByOrderId(orderId).ifPresent(order -> this.bookService.assignUserToBook(order));
            orderService.deleteOrder(orderId);
        }
        if (request.getRequestURI().endsWith("decline")) {
            orderService.deleteOrder(orderId);
        }
    }
}
