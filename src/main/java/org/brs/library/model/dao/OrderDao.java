package org.brs.library.model.dao;

import org.brs.library.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findAll(long numberOfItems, long offset);

    Optional<Order> findById(Long id);

    void delete(Long id);

    long getNumberOfOrders();
}
