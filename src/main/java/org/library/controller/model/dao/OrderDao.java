package org.library.controller.model.dao;

import org.library.controller.model.entity.Order;

public interface OrderDao extends GenericDao<Order> {
    public void acceptOrder(Long id);
}
