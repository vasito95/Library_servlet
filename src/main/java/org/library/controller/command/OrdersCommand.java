package org.library.controller.command;

import org.library.controller.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command {
    private OrderService orderService;

    public OrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if(request.getRequestURI().endsWith("accept")){
            System.out.println(request.getParameter("orderId"));
            System.out.println("ACCEPT");
            return "redirect:/admin/orders";
        }
        if(request.getRequestURI().endsWith("decline")){
            System.out.println(request.getParameter("orderId"));
            System.out.println("DECLINE");
            return "redirect:/admin/orders";
        }
        request.setAttribute("orders", orderService.findAll());
        return "/WEB-INF/admin/orders.jsp";
    }
}
