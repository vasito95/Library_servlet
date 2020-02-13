package org.brs.library.model.dao;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);
    void delete(Long id);
}
