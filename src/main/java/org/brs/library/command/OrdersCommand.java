package org.brs.library.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brs.library.helper.ValidationHelper;
import org.brs.library.model.dao.impl.JDBCOrderDao;
import org.brs.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command {
    public static final int DEFAULT_NUMBER_OF_ITEMS_PER_PAGE = 5;
    public static final int MAXIMUM_ALLOWED_ITEMS_PER_PAGE = 50;
    private static Logger LOG = LogManager.getLogger(JDBCOrderDao.class);
    private OrderService orderService;

    public OrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("page");
        String numberOfItems = request.getParameter("numberOfItems");
        if (ValidationHelper.isStringNullOrEmpty(page, numberOfItems)
                || !page.matches("[0-9]+")
                || !numberOfItems.matches("[0-9]+")) {
            request.setAttribute("numberOfPages", Math.ceil(orderService.getNumberOfOrders() * 1.0 / DEFAULT_NUMBER_OF_ITEMS_PER_PAGE));
            request.setAttribute("numberOfOrders", DEFAULT_NUMBER_OF_ITEMS_PER_PAGE);
            request.setAttribute("orders", orderService.findAll(DEFAULT_NUMBER_OF_ITEMS_PER_PAGE, 0));
            return "/WEB-INF/admin/orders.jsp";
        }
        long numberOfItemsParsed = Long.parseLong(numberOfItems);
        long pageParsed = Long.parseLong(page);

        if (numberOfItemsParsed <= 0 || numberOfItemsParsed > MAXIMUM_ALLOWED_ITEMS_PER_PAGE
                || pageParsed < 0) {
            return "redirect:/admin/orders";
        }
        request.setAttribute("numberOfPages", Math.ceil(orderService.getNumberOfOrders() * 1.0 / numberOfItemsParsed));
        request.setAttribute("numberOfOrders", numberOfItemsParsed);
        request.setAttribute("orders", orderService.findAll(numberOfItemsParsed, Long.parseLong(page) * numberOfItemsParsed));
        return "/WEB-INF/admin/orders.jsp";
    }
}