package org.brs.library.service;

import org.brs.library.model.dao.OrderDao;
import org.brs.library.model.entity.Order;

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
        return this.orderDao.findById(orderId);
    }

    public List<Order> findAll(long numberOfItems, long offset) {
        return this.orderDao.findAll(numberOfItems, offset);
    }

    public void deleteOrder(Long id) {
        this.orderDao.delete(id);
    }

    public long getNumberOfOrders() {
        return this.orderDao.getNumberOfOrders();
    }
}
