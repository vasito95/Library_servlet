package org.library.controller.command;

import org.library.controller.model.entity.Order;
import org.library.controller.model.services.BookService;
import org.library.controller.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        if(id == null || id.equals("")){
            request.setAttribute("orders", orderService.findAll());
            return "/WEB-INF/admin/orders.jsp";
        }
        Long orderId = Long.parseLong(id);
        if(request.getRequestURI().endsWith("accept")){
            orderService.findByOrderId(orderId).ifPresent(order -> this.bookService.assignUserToBook(order));
            orderService.deleteOrder(orderId);
            return "redirect:/admin/orders";
        }
        if(request.getRequestURI().endsWith("decline")){
            orderService.deleteOrder(orderId);
            return "redirect:/admin/orders";
        }
        request.setAttribute("orders", orderService.findAll());
        return "/WEB-INF/admin/orders.jsp";
    }
}
