package org.library.controller.model.dao;

import org.library.controller.model.entity.Order;

import java.util.Optional;

public interface OrderDao extends GenericDao<Order> {

    Optional<Order> findByOrderId(Long id);
}
