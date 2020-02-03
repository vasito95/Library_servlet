package org.library.controller.model.services;

import org.library.controller.model.dao.OrderDao;
import org.library.controller.model.entity.Order;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void placeOrder(Order order) {
        this.orderDao.create(order);
    }

    public Optional<Order> findByOrderId(Long orderId) {
        return this.orderDao.findByOrderId(orderId);
    }

    public List<Order> findAll() {
        return this.orderDao.findAll();
    }

    public void deleteOrder(Long id) {
        this.orderDao.delete(id);
    }
}
