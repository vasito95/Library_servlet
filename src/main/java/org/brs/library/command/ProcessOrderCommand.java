package org.brs.library.command;

import org.brs.library.service.BookService;
import org.brs.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ProcessOrderCommand implements Command {

    private OrderService orderService;
    private BookService bookService;

    public ProcessOrderCommand(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String id = request.getParameter("orderId");
        Long orderId = Long.parseLong(id);
        processOrder(request, orderId);
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
